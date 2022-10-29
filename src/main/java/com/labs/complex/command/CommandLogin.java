package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.Person;
import com.labs.complex.db.DBConnection;
import com.labs.complex.util.ConsoleInput;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CommandLogin implements Command {
    private Application application;

    public CommandLogin(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        Scanner scanner = ConsoleInput.getScanner();
        System.out.print("Login: ");
        String login = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

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
                        System.out.println("Can't login");
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
                        System.out.println("Can't login");
                        //TODO SEND MESSAGE TO MAIL
                    }
                    statement.close();
                    break;
                case 'P':
                    String queryUser = "SELECT * FROM [Person].[Person] AS Person " +
                            "LEFT JOIN [Work].[Work] AS MainWork ON Person.[MainWorkID] = MainWork.[WorkID] " +
                            "LEFT JOIN [Work].[Work] AS AddWork ON Person.[AdditionalWorkID] = AddWork.[WorkID] " +
                            "WHERE [AccountID] = ?";
                    statement = DBConnection.getInstance().prepareStatement(queryUser);
                    statement.setInt(1, accountID);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        application.setAccount(
                                new User(login,
                                new Person(resultSet.getString("Name"), resultSet.getString("Surname"),
                                resultSet.getInt("Salary"), resultSet.getInt("Kids"),
                                resultSet.getString(10), resultSet.getString(13)))
                        );
                    }
                    else {
                        System.out.println("Can't login");
                        //TODO SEND MESSAGE TO MAIL
                    }
                    statement.close();
                    break;
                default:
                    System.out.println("Can't login");
                    //TODO SEND MESSAGE TO MAIL
                    break;
            }
        }
        catch (SQLException exception) {
            System.out.println("Error");
            exception.printStackTrace();
        }
    }
}
