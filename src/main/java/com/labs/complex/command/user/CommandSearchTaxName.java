package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;

public class CommandSearchTaxName implements Command {
    private User account;
    private String filter;
    private List<Tax> taxList;

    public CommandSearchTaxName(IAccount account, String filter) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
        this.filter = filter.toLowerCase();
        this.taxList = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (Tax tax : account.getTaxList()) {
            if (tax.getDescription().toLowerCase().contains(filter)) {
                taxList.add(tax);
            }
        }
    }

    public List<Tax> getTaxList() {
        return taxList;
    }
}
