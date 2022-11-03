package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.log.LogUtilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command class to add action
 */
public class CommandAddAction implements Command {
    private User account;
    private int actionID;
    private int value;
    private boolean add = false;

    private static final Logger logger = Logger.getLogger(CommandAddAction.class.getName());

    public CommandAddAction(IAccount account, int actionID, int value) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
        this.actionID = actionID;
        this.value = value;
    }

    @Override
    public void execute() {
        LogUtilities.setupLogger(logger);
        PreparedStatement statement;

        String addTax = "INSERT INTO [Person].[PersonAction] VALUES (?, ?, ?, DEFAULT)";
        statement = DBConnection.getInstance().prepareStatement(addTax);
        Integer id;
        try {
            id = findPersonID();
            if (id == null) {
                return;
            }
            statement.setInt(1, id);
            statement.setInt(2, actionID);
            statement.setInt(3, value);
            statement.executeUpdate();
            statement.close();
            add = true;
        }
        catch (SQLException exception) {
            if (exception.getMessage().contains("Violation of PRIMARY KEY constraint 'PK_PersonAction'")) {
                System.out.println("You've already added this action today.");
            }
            else {
                logger.log(Level.SEVERE, "Database or SQL Error", exception);
            }
        }
    }

    private Integer findPersonID() {
        PreparedStatement statement;

        String getAccountID = "SELECT PersonID FROM [dbo].[Account] [Account] \n" +
                "INNER JOIN [Person].[Person] [Person] ON [Person].[AccountID] = [Account].[AccountID]\n" +
                "WHERE [Account].[Login] = ?";
        statement = DBConnection.getInstance().prepareStatement(getAccountID);
        Integer id = null;
        try {
            statement.setString(1, account.getLogin());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                statement.close();
            }
            statement.close();
        }
        catch (SQLException exception) {
            logger.log(Level.SEVERE, "Database or SQL Error", exception);
        }
        return id;
    }

    public boolean isAdd() {
        return add;
    }
}
