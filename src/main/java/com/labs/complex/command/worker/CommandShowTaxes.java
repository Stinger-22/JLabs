package com.labs.complex.command.worker;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandShowTaxes implements Command {
    private Worker account;

    public CommandShowTaxes(IAccount account) throws AccessDeniedException {
        if (!(account instanceof Worker)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Worker) account;
    }

    @Override
    public void execute() {
        DBConnection connection = DBConnection.getInstance();
        Statement statement;
        String selectTaxes = "SELECT TaxID, Description, IsAbsolute FROM [Tax].[Tax] ORDER BY Description";
        statement = connection.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(selectTaxes);
            for (int i = 1; resultSet.next(); i++) {
                System.out.println("N: " + i + " | ID: " + resultSet.getInt(1) + " | Description: " + resultSet.getString(2) +
                        " | Absoulute: " + (resultSet.getByte(3) == 1));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
