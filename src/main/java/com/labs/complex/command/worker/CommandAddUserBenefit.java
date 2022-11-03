package com.labs.complex.command.worker;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandFindAccountID;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command class to add benefit for user
 */
public class CommandAddUserBenefit implements Command {
    private Worker account;
    private String login;
    private int benefitID;

    public CommandAddUserBenefit(IAccount account, String login, int benefitID) throws AccessDeniedException {
        if (!(account instanceof Worker)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Worker) account;
        this.login = login;
        this.benefitID = benefitID;
    }

    @Override
    public void execute() {
        PreparedStatement statement;

        String addBenefit = "INSERT INTO [Person].[PersonBenefit] VALUES (?, ?)";
        statement = DBConnection.getInstance().prepareStatement(addBenefit);
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
            statement.setInt(1, id);
            statement.setInt(2, benefitID);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException | AccessDeniedException exception) {
            exception.printStackTrace();
        }
    }
}
