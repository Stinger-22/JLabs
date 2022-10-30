package com.labs.complex.command.admin;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.being.Person;
import com.labs.complex.command.Command;
import com.labs.complex.command.CommandFindAccountID;
import com.labs.complex.command.exception.AccessDeniedException;
import com.labs.complex.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandShowUserPerson implements Command {
    private Admin account;
    private String login;
    private Person person;

    public CommandShowUserPerson(IAccount account, String login) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
        this.login = login;
    }

    @Override
    public void execute() {
        try {
            CommandFindAccountID finder = new CommandFindAccountID(account, login);
            finder.execute();
            if (finder.getId() == null) {
                return;
            }
            showUser(finder.getId());
        } catch (SQLException | AccessDeniedException exception) {
            exception.printStackTrace();
        }
    }

    private void showUser(int id) throws SQLException {
        DBConnection connection = DBConnection.getInstance();
        PreparedStatement statement;
        String selectPerson = "SELECT * FROM [Person].[Person] Person " +
                "LEFT JOIN [Work].[Work] w1 ON Person.[MainWorkID] = w1.[WorkID] " +
                "LEFT JOIN [Work].[Work] w2 ON Person.[AdditionalWorkID] = w2.[WorkID] " +
                "WHERE Person.AccountID = ?";

        statement = connection.prepareStatement(selectPerson);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            person = new Person(resultSet.getString(3), resultSet.getString(4),
                    resultSet.getDouble(5), resultSet.getInt(6), resultSet.getString(10),
                    resultSet.getString(13)
            );
        }
    }

    public Person getPerson() {
        return person;
    }
}
