package com.labs.complex.command;

import com.labs.complex.Application;

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
