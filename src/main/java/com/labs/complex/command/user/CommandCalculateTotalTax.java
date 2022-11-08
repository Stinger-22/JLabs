package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Action;
import com.labs.complex.being.Calculatable;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.log.LogUtilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command class for calculating overall tax value
 */
public class CommandCalculateTotalTax implements Command {
    private User account;
    private double value = 0;

    private static final Logger logger = Logger.getLogger(CommandCalculateTotalTax.class.getName());

    public CommandCalculateTotalTax(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        LogUtilities.setupLogger(logger);
        for (Tax tax : account.getTaxList()) {
            value += tax.calculate(account);
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
                    value += action.calculate(account);
                }
            }
            statement.close();
        }
        catch (SQLException exception) {
            logger.log(Level.SEVERE, "Database or SQL Error", exception);
        }
    }

    /**
     * Get overall tax value
     * @return overall tax value
     */
    public double getValue() {
        return value;
    }
}
