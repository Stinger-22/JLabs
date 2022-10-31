package com.labs.complex.command;

import com.labs.complex.Application;
import com.labs.complex.db.DBConnection;
import com.labs.complex.log.LogUtilities;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExit implements Command {
    private Application application;

    private static final Logger logger = Logger.getLogger(CommandExit.class.getName());

    public CommandExit(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        LogUtilities.setupLogger(logger);
        try {
            DBConnection.getInstance().close();
            application.setAccount(null);
            logger.log(Level.FINE, "Successfully exited");
        }
        catch (SQLException exception) {
            logger.log(Level.SEVERE, "Connection to database can't be closed", exception);
        }
    }
}