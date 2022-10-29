package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.command.exception.AccessDeniedException;

public class CommandSearchTax implements Command {
    private User account;
    private String filter;

    public CommandSearchTax(IAccount account, String filter) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
        this.filter = filter.toLowerCase();
    }

    @Override
    public void execute() {
        for (Tax tax : account.getTaxList()) {
            if (tax.getDescription().toLowerCase().contains(filter)) {
                System.out.println(tax);
            }
        }
    }
}
