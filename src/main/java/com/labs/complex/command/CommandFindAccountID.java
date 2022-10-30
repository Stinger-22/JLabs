package com.labs.complex.command;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandFindAccountID implements Command {
    private IAccount account;
    private String login;
    private Integer id;

    public CommandFindAccountID(IAccount account, String login) throws AccessDeniedException {
        if (!(account instanceof Admin || account instanceof Worker)) {
            if (!(account instanceof User)) {
                throw new AccessDeniedException(account);
            }
        }
        this.account = account;
        this.login = login;
    }

    @Override
    public void execute() {
        PreparedStatement statement;

        String getAccountID = "SELECT AccountID FROM [dbo].[Account] WHERE Login = ?";
        statement = DBConnection.getInstance().prepareStatement(getAccountID);
        try {
            if (account instanceof User) {
                statement.setString(1, ((User) account).getLogin());
            }
            else {
                statement.setString(1, login);
            }
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

    private void executeForUser() {

    }

    public Integer getId() {
        return id;
    }
}