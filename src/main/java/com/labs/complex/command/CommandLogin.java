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

        String queryAdmin = "SELECT * FROM [dbo].[Admin] WHERE [Login] = '" + login + "' AND [Password] = '" + password + "'";
        String queryWorker = "SELECT * FROM [dbo].[SystemWorker] WHERE [Login] = '" + login + "' AND [Password] = '" + password + "'";
        String queryUser = "SELECT * FROM [Person].[Person] WHERE [Login] = '" + login + "' AND [Password] = '" + password + "'";

        Statement statement;
        ResultSet resultSet;

        statement = DBConnection.getInstance().createStatement();
        try {
            resultSet = statement.executeQuery(queryAdmin);
            if (resultSet.next()) {
                application.setAccount(new Admin(login));
            }
            resultSet = statement.executeQuery(queryWorker);
            if (resultSet.next()) {
                application.setAccount(new Worker(login, resultSet.getString("Name"), resultSet.getString("Surname")));
            }
            resultSet = statement.executeQuery(queryUser);
            if (resultSet.next()) {
                String job = "SELECT * FROM [Person].[Person] AS Person " +
                        "LEFT JOIN [Work].[Work] AS MainWork ON Person.[MainWorkID] = MainWork.[WorkID] " +
                        "LEFT JOIN [Work].[Work] AS AddWork ON Person.[AdditionalWorkID] = AddWork.[WorkID] " +
                        "WHERE [Login] = '" + login + "' AND [Password] = '" + password + "'";
                resultSet = statement.executeQuery(job);
                resultSet.next();
                application.setAccount(new User(login, resultSet.getString("Name"), resultSet.getString("Surname"),
                        resultSet.getInt("Salary"), resultSet.getInt("Kids"),
                        resultSet.getString(11), resultSet.getString(14)));
            }
        }
        catch (SQLException exception) {
            System.out.println("Error");
            exception.printStackTrace();
        }
    }
}
