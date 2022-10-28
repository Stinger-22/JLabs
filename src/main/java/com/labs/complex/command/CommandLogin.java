package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.account.Admin;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.db.DBConnection;
import com.labs.complex.util.ConsoleInput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CommandLogin implements Command {
    Application application;

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

        String queryAccount = "SELECT * FROM [dbo].[Account] WHERE [Login] = '" + login + "' AND [Password] = '" + password + "'";

        Statement statement;
        ResultSet resultSet;

        statement = DBConnection.getInstance().createStatement();
        try {
            resultSet = statement.executeQuery(queryAccount);
            if (!resultSet.next()) {
                System.out.println("Login failed.");
                return;
            }

            char role = resultSet.getString("Role").charAt(0);
            int accountID = resultSet.getInt("AccountID");
            switch (role) {
                case 'A':
                    String queryAdmin = "SELECT * FROM [dbo].[Admin] WHERE [AccountID] = " + accountID;
                    resultSet = statement.executeQuery(queryAdmin);
                    if (resultSet.next()) {
                        application.setAccount(new Admin(login));
                    }
                    else {
                        System.out.println("Can't login");
                        //TODO SEND MESSAGE TO MAIL
                    }
                    break;
                case 'W':
                    String queryWorker = "SELECT * FROM [dbo].[SystemWorker] WHERE [AccountID] = " + accountID;
                    resultSet = statement.executeQuery(queryWorker);
                    if (resultSet.next()) {
                        application.setAccount(new Worker(login, resultSet.getString("Name"), resultSet.getString("Surname")));
                    }
                    else {
                        System.out.println("Can't login");
                        //TODO SEND MESSAGE TO MAIL
                    }
                    break;
                case 'P':
                    String queryUser = "SELECT * FROM [Person].[Person] AS Person " +
                        "LEFT JOIN [Work].[Work] AS MainWork ON Person.[MainWorkID] = MainWork.[WorkID] " +
                        "LEFT JOIN [Work].[Work] AS AddWork ON Person.[AdditionalWorkID] = AddWork.[WorkID] " +
                        "WHERE [AccountID] = " + accountID;
                    resultSet = statement.executeQuery(queryUser);
                    if (resultSet.next()) {
                        application.setAccount(new User(login, resultSet.getString("Name"), resultSet.getString("Surname"),
                                resultSet.getInt("Salary"), resultSet.getInt("Kids"),
                                resultSet.getString(10), resultSet.getString(13)));
                    }
                    else {
                        System.out.println("Can't login");
                        //TODO SEND MESSAGE TO MAIL
                    }
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
