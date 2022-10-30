package com.labs.complex.command;

import com.labs.complex.account.IAccount;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandFindAccountID implements Command {
    private IAccount account;
    private String login;
    private Integer id;

    public CommandFindAccountID(IAccount account, String login) {
        this.account = account;
        this.login = login;
    }

    @Override
    public void execute() {
        PreparedStatement statement;

        String getAccountID = "SELECT AccountID FROM [dbo].[Account] WHERE Login = ?";
        statement = DBConnection.getInstance().prepareStatement(getAccountID);
        try {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                statement.close();
            }
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }
}