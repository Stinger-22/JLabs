package com.labs.complex.command.admin;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandFindAccountID;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command class to delete user
 */
public class CommandDeleteUser implements Command {
    private Admin account;
    private String login;

    public CommandDeleteUser(IAccount account, String login) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
        this.login = login;
    }

    @Override
    public void execute() {
        try {
            CommandFindAccountID finder = new CommandFindAccountID(account, login);
            finder.execute();
            if (finder.getId() != null) {
                deleteAccount(finder.getId());
            }
        } catch (SQLException | AccessDeniedException exception) {
            exception.printStackTrace();
        }
    }

    private void deleteAccount(int accountID) throws SQLException {
        DBConnection connection = DBConnection.getInstance();
        PreparedStatement statement;

        String deletePerson = "DELETE FROM [dbo].[Account] WHERE AccountID = ?";
        statement = connection.prepareStatement(deletePerson);
        statement.setInt(1, accountID);
        statement.executeUpdate();
        statement.close();
    }
}