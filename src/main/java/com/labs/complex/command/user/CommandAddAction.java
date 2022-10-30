package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.account.Worker;
import com.labs.complex.being.Action;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandFindAccountID;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandAddAction implements Command {
    private User account;
    private int actionID;
    private int value;

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
        PreparedStatement statement;

        String addTax = "INSERT INTO [Person].[PersonAction] VALUES (?, ?, ?, DEFAULT)";
        statement = DBConnection.getInstance().prepareStatement(addTax);
        int id;
        try {
            CommandFindAccountID finder = new CommandFindAccountID(account, account.getLogin());
            finder.execute();
            if (finder.getId() != null) {
                id = finder.getId();
            }
            else {
                return;
            }
            statement.setInt(1, id);
            statement.setInt(2, actionID);
            statement.setInt(3, value);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException | AccessDeniedException exception) {
            exception.printStackTrace();
        }
    }
}
