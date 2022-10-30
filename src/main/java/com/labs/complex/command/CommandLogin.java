package com.labs.complex.command;

import com.labs.complex.launch.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.*;
import com.labs.complex.command.exception.NoRightException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandLogin implements Command {
    private Application application;
    private String login;
    private String password;

    public CommandLogin(Application application, String login, String password) {
        this.application = application;
        this.login = login;
        this.password = password;
    }

    @Override
    public void execute() {
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
                    }
                    else {
                        throw new NoRightException(login, role);
                        //TODO SEND MESSAGE TO MAIL
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
                    }
                    else {
                        throw new NoRightException(login, role);
                        //TODO SEND MESSAGE TO MAIL
                    }
                    statement.close();
                    break;
                case 'P':
                    setupUser(accountID);
                    break;
                default:
                    throw new NoRightException(login, role);
                    //TODO SEND MESSAGE TO MAIL
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void setupUser(int accountID) throws SQLException, NoRightException {
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
            //TODO SEND MESSAGE TO MAIL
            throw new NoRightException(login, 'P');
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
        if (taxList.isEmpty()) {
            taxList = null;
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
        if (benefitList.isEmpty()) {
            benefitList = null;
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
        if (actionList.isEmpty()) {
            actionList = null;
        }

        statement.close();

        application.setAccount(new User(login, person, taxList, benefitList, actionList));
    }
}
