package com.labs.complex.command;

import com.labs.complex.launch.Application;
import com.labs.complex.db.DBConnection;

import java.sql.SQLException;

public class CommandExit implements Command {
    private Application application;

    public CommandExit(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        try {
            DBConnection.getInstance().close();
            application.setAccount(null);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}