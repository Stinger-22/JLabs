package com.labs.complex.command.worker;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.Worker;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandShowBenefits implements Command {
    private Worker account;

    public CommandShowBenefits(IAccount account) throws AccessDeniedException {
        if (!(account instanceof Worker)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Worker) account;
    }

    @Override
    public void execute() {
        DBConnection connection = DBConnection.getInstance();
        Statement statement;
        String selectBenefits = "SELECT BenefitID, Description FROM [Benefit].[Benefit] ORDER BY Description";
        statement = connection.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(selectBenefits);
            for (int i = 1; resultSet.next(); i++) {
                System.out.println("N: " + i + " | ID: " + resultSet.getInt(1) + " | Description: " + resultSet.getString(2));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
