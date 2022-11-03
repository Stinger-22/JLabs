package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.Action;
import com.labs.complex.being.Benefit;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.NoRightException;
import com.labs.complex.log.LogUtilities;
import com.labs.complex.mail.MailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command class for logging in to application
 */
public class CommandLogin implements Command {
    private Application application;
    private String login;
    private String password;

    private static final Logger logger = Logger.getLogger(CommandLogin.class.getName());

    public CommandLogin(Application application, String login, String password) {
        this.application = application;
        this.login = login;
        this.password = password;
    }

    @Override
    public void execute() {
        LogUtilities.setupLogger(logger);
        PreparedStatement statement;
        ResultSet resultSet;

        String queryAccount = "SELECT * FROM [dbo].[Account] WHERE [Login] = ? AND [Password] = ?";
        statement = DBConnection.getInstance().prepareStatement(queryAccount);
        try {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Login failed.");
                logger.log(Level.WARNING, "Trying to login with wrong login or password", "Login: " + login + "Password: " + password);
                statement.close();
                return;
            }

            char role = resultSet.getString("Role").charAt(0);
            int accountID = resultSet.getInt("AccountID");
            statement.close();
            switch (role) {
                case 'A':
                    String queryAdmin = "SELECT * FROM [dbo].[Admin] WHERE [AccountID] = ?";
                    statement = DBConnection.getInstance().prepareStatement(queryAdmin);
                    statement.setInt(1, accountID);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        application.setAccount(new Admin(login));
                        logger.log(Level.FINE, "Successfully logged in as admin.", application.getAccount());
                    }
                    else {
                        RuntimeException exception = new NoRightException(login, role);
                        logger.log(Level.SEVERE, "Admin account exists with no AdminID", exception);
                        MailSender.sendErrorsToDeveloper();
                    }
                    statement.close();
                    break;
                case 'W':
                    String queryWorker = "SELECT * FROM [dbo].[SystemWorker] WHERE [AccountID] = ?";
                    statement = DBConnection.getInstance().prepareStatement(queryWorker);
                    statement.setInt(1, accountID);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        application.setAccount(new Worker(login, resultSet.getString("Name"), resultSet.getString("Surname")));
                        logger.log(Level.FINE, "Successfully logged in as worker.", application.getAccount());
                    }
                    else {
                        RuntimeException exception = new NoRightException(login, role);
                        logger.log(Level.SEVERE, "Worker account exists with no WorkerID", exception);
                        MailSender.sendErrorsToDeveloper();
                    }
                    statement.close();
                    break;
                case 'P':
                    setupUser(accountID);
                    break;
                default:
                    RuntimeException exception = new NoRightException(login, role);
                    logger.log(Level.SEVERE, "Alien role", exception);
                    MailSender.sendErrorsToDeveloper();
            }
        }
        catch (SQLException exception) {
            logger.log(Level.SEVERE, "Database or SQL Error", exception);
        }
        catch (MessagingException | IOException exception) {
            logger.log(Level.WARNING, "Can't send error log to developer", exception);
        }
    }

    private void setupUser(int accountID) throws SQLException, NoRightException, MessagingException, IOException {
        PreparedStatement statement;
        ResultSet r;
        Person person;
        List<Tax> taxList = new ArrayList<>();
        List<Benefit> benefitList = new ArrayList<>();
        List<Action> actionList = new ArrayList<>();
        // Person
        int personID;
        String queryPerson = "SELECT * FROM [Person].[Person] AS Person " +
                "LEFT JOIN [Work].[Work] AS MainWork ON Person.[MainWorkID] = MainWork.[WorkID] " +
                "LEFT JOIN [Work].[Work] AS AddWork ON Person.[AdditionalWorkID] = AddWork.[WorkID] " +
                "WHERE [AccountID] = ?";
        statement = DBConnection.getInstance().prepareStatement(queryPerson);
        statement.setInt(1, accountID);
        r = statement.executeQuery();
        if (r.next()) {
            person = new Person(r.getString("Name"), r.getString("Surname"),
                    r.getInt("Salary"), r.getInt("Kids"),
                    r.getString(10), r.getString(13));
            personID = r.getInt("PersonID");
        }
        else {
            RuntimeException exception = new NoRightException(login, 'P');
            logger.log(Level.SEVERE, "Person account exists with no PersonID", exception);
            MailSender.sendErrorsToDeveloper();
            return;
        }
        statement.close();
        // List tax
        String queryTax = "SELECT Tax.[Description], PersonTax.[Value], Tax.[isAbsolute] " +
                "FROM [Person].[PersonTax] PersonTax " +
                "INNER JOIN [Tax].[Tax] Tax ON Tax.[TaxID] = PersonTax.[TaxID] " +
                "WHERE [PersonID] = ?";
        statement = DBConnection.getInstance().prepareStatement(queryTax);
        statement.setInt(1, personID);

        r = statement.executeQuery();
        while (r.next()) {
            taxList.add(new Tax(r.getString(1), r.getInt(2), (r.getByte(3) == 1)));
        }

        statement.close();
        // List benefit
        String queryBenefit = "SELECT Benefit.[Description] FROM [Person].[PersonBenefit] PersonBenefit " +
                "INNER JOIN [Benefit].[Benefit] Benefit ON Benefit.[BenefitID] = PersonBenefit.[BenefitID] " +
                "WHERE [PersonID] = ?";
        statement = DBConnection.getInstance().prepareStatement(queryBenefit);
        statement.setInt(1, personID);

        r = statement.executeQuery();
        while (r.next()) {
            benefitList.add(new Benefit(r.getString(1)));
        }

        statement.close();
        // List action
        String queryAction = "SELECT [Name], [Value], [Date] FROM [Person].[PersonAction] [PersonAction] " +
                "INNER JOIN [Action].[Action] [Action] ON [Action].[ActionID] = PersonAction.[ActionID] " +
                "WHERE [PersonID] = ?";
        statement = DBConnection.getInstance().prepareStatement(queryAction);
        statement.setInt(1, personID);

        r = statement.executeQuery();
        while (r.next()) {
            actionList.add(new Action(r.getString(1), r.getInt(2), r.getDate(3)));
        }

        statement.close();

        application.setAccount(new User(login, person, taxList, benefitList, actionList));
    }
}
