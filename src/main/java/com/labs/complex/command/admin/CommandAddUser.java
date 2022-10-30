package com.labs.complex.command.admin;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.being.Person;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;
import com.labs.complex.exception.ExistingLoginException;
import com.labs.complex.exception.WorkNotExistsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CommandAddUser implements Command {
    private Admin account;
    private String login;
    private String password;
    private Person person;

    public CommandAddUser(IAccount account, String login, String password, Person person) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
        this.login = login;
        this.password = password;
        this.person = person;
    }

    @Override
    public void execute() {
        try {
            Integer mainWorkID = null, additionalWorkID = null;
            if (person.getMainWork() != null) {
                mainWorkID = checkWork(person.getMainWork());
            }
            if (person.getAdditionalWork() != null) {
                additionalWorkID = checkWork(person.getAdditionalWork());
            }
            int id = createAccount();
            createPerson(id, mainWorkID, additionalWorkID);
        }
        catch (SQLException | WorkNotExistsException exception) {
            exception.printStackTrace();
        }
        catch (ExistingLoginException exception) {
            System.out.println("This login is already used.");
        }
    }

    private int createAccount() throws SQLException, ExistingLoginException {
        PreparedStatement statement;

        String createAccount = "INSERT INTO [dbo].[Account] VALUES (?, ?, 'P')";
        statement = DBConnection.getInstance().prepareStatement(createAccount);
        statement.setString(1, login);
        statement.setString(2, password);
        try {
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            throw new ExistingLoginException(account, login);
        }
        statement.close();
        int id;

        String getAccountID = "SELECT AccountID FROM [dbo].[Account] WHERE Login = ? AND Password = ?";
        statement = DBConnection.getInstance().prepareStatement(getAccountID);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        id = resultSet.getInt(1);
        statement.close();
        return id;
    }

    private void createPerson(int id, Integer mainWorkID, Integer additionalWorkID) throws SQLException {
        DBConnection connection = DBConnection.getInstance();
        PreparedStatement statement;

        String createPerson = "INSERT INTO [Person].[Person] VALUES (?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(createPerson);
        statement.setInt(1, id);
        statement.setString(2, person.getName());
        statement.setString(3, person.getSurname());
        statement.setDouble(4, person.getSalary());
        statement.setInt(5, person.getKids());
        if (mainWorkID == null) {
            statement.setNull(6, Types.INTEGER);
        }
        else {
            statement.setInt(6, mainWorkID);
        }
        if (additionalWorkID == null) {
            statement.setNull(7, Types.INTEGER);
        }
        else {
            statement.setInt(7, additionalWorkID);
        }
        statement.executeUpdate();
        statement.close();
    }

    private int checkWork(String workName) throws SQLException, WorkNotExistsException {
        DBConnection connection = DBConnection.getInstance();
        PreparedStatement statement;
        String searchWork = "SELECT [WorkID] FROM [Work].[Work] WHERE [Name] = ?";
        int workID;
        statement = connection.prepareStatement(searchWork);
        statement.setString(1, workName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            workID = resultSet.getInt(1);
            statement.close();
            return workID;
        }
        statement.close();
        throw new WorkNotExistsException(workName);
    }
}
