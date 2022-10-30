package com.labs.complex.command.worker;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandFindAccountID;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandAddUserTax implements Command {
    private Worker account;
    private String login;
    private int taxID;
    private int value;

    public CommandAddUserTax(IAccount account, String login, int taxID, int value) throws AccessDeniedException {
        if (!(account instanceof Worker)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Worker) account;
        this.login = login;
        this.taxID = taxID;
        this.value = value;
    }

    @Override
    public void execute() {
        PreparedStatement statement;

        String addTax = "INSERT INTO [Person].[PersonTax] VALUES (?, ?, ?)";
        statement = DBConnection.getInstance().prepareStatement(addTax);
        int id;
        try {
            CommandFindAccountID finder = new CommandFindAccountID(account, login);
            finder.execute();
            if (finder.getId() != null) {
                id = finder.getId();
            }
            else {
                return;
            }
        }
        catch (AccessDeniedException exception) {
            exception.printStackTrace();
            return;
        }
        try {
            statement.setInt(1, id);
            statement.setInt(2, taxID);
            statement.setInt(3, value);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
