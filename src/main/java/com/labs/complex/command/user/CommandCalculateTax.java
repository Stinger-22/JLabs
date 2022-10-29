package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

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
    }

    public double getValue() {
        return value;
    }
}
