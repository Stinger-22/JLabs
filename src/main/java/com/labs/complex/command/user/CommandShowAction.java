package com.labs.complex.command.user;

import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Action;
import com.labs.complex.command.Command;
import com.labs.complex.exception.AccessDeniedException;

import java.util.List;

/**
 * Command class to show user actions
 */
public class CommandShowAction implements Command {
    private User account;

    public CommandShowAction(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        List<Action> actionList = account.getActionList();
        for (Action action : actionList) {
            System.out.println("Action: " + action.getName());
            System.out.println("Value: " + action.getValue());
            System.out.println("Tax: " + (action.getPercent() * action.getValue()));
        }
    }
}
