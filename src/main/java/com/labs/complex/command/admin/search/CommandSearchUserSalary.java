package com.labs.complex.command.admin.search;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;
import com.labs.complex.db.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandSearchUserSalary implements Command {
    private Admin account;
    private double min;
    private double max;

    public CommandSearchUserSalary(IAccount account, double min, double max) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
        this.min = min;
        this.max = max;
    }

    @Override
    public void execute() {
        String searchSalary = "SELECT * FROM [dbo].[Account] Account\n" +
                "INNER JOIN [Person].[Person] Person ON Person.[AccountID] = Account.[AccountID]\n" +
                "LEFT JOIN [Work].[Work] w1 ON w1.[WorkID] = Person.[MainWorkID]\n" +
                "LEFT JOIN [Work].[Work] w2 ON w2.[WorkID] = Person.[AdditionalWorkID]\n" +
                "WHERE Person.[Salary] BETWEEN ? AND ?";

        PreparedStatement statement;
        statement = DBConnection.getInstance().prepareStatement(searchSalary);
        try {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet r = statement.executeQuery();
            Utility.printSearchUser(r);
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
