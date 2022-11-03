package com.labs.complex.command;

import com.labs.complex.Application;

/**
 * Command class to log out from application
 */
public class CommandLogout implements Command {
    private Application application;

    public CommandLogout(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.setAccount(null);
    }
}
