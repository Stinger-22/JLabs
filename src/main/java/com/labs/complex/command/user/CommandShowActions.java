package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command class to show available actions
 */
public class CommandShowActions implements Command {
    private User account;

    private static final Logger logger = Logger.getLogger(CommandShowActions.class.getName());

    public CommandShowActions(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        DBConnection connection = DBConnection.getInstance();
        Statement statement;
        String selectActions = "SELECT ActionID, Name FROM [Action].[Action] ORDER BY Name";
        statement = connection.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(selectActions);
            for (int i = 1; resultSet.next(); i++) {
                System.out.println("N: " + i + " | ID: " + resultSet.getInt(1) + " | Name: " + resultSet.getString(2));
            }
        }
        catch (SQLException exception) {
            logger.log(Level.SEVERE, "Database or SQL Error", exception);
        }
    }
}
