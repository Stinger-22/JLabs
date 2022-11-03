package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Command class for searching tax by value
 */
public class CommandSearchTaxValue implements Command {
    private User account;
    private int min;
    private int max;
    private List<Tax> taxList;

    public CommandSearchTaxValue(IAccount account, int min, int max) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
        this.min = min;
        this.max = max;
        this.taxList = new ArrayList<>();
    }

    @Override
    public void execute() {
        double value;
        for (Tax tax : account.getTaxList()) {
            if (!tax.isAbsolute())
                value = account.getPerson().getSalary() / 100 * tax.getValue();
            else {
                value = tax.getValue();
            }
            if (value >= min && value <= max) {
                taxList.add(tax);
            }
        }
    }

    /**
     * Get found taxes
     * @return found taxes
     */
    public List<Tax> getTaxList() {
        return taxList;
    }
}
