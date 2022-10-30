package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Action;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandCalculateTax implements Command {
    private User account;
    private double value = 0;

    public CommandCalculateTax(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        for (Tax tax : account.getTaxList()) {
            if (tax.isAbsolute()) {
                value += tax.getValue();
            }
            else {
                value += account.getPerson().getSalary() / 100.0 * tax.getValue();
            }
        }
        PreparedStatement statement;
        String dateDiff = "SELECT DATEDIFF(month, GETDATE(), ?)";
        statement = DBConnection.getInstance().prepareStatement(dateDiff);
        ResultSet resultSet;
        try {
            for (Action action : account.getActionList()) {
                statement.setDate(1, action.getDate());
                resultSet = statement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) == 0) {
                    value += action.getValue() / 100 * 2;
                }
            }
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public double getValue() {
        return value;
    }
}
