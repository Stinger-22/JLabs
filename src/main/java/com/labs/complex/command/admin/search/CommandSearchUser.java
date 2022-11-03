package com.labs.complex.command.admin.search;

import com.labs.complex.account.Admin;
import com.labs.complex.account.IAccount;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.db.Utility;
import com.labs.complex.exception.AccessDeniedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Command class for searching user by String object
 */
public class CommandSearchUser implements Command {
    private Admin account;
    private Object filter;
    private int searchBy;

    public CommandSearchUser(IAccount account, int searchBy, Object filter) throws AccessDeniedException {
        if (!(account instanceof Admin)) {
            throw new AccessDeniedException(account);
        }
        this.account = (Admin) account;
        this.searchBy = searchBy;
        this.filter = filter;
    }

    @Override
    public void execute() {
        String searchTemplate = "SELECT * FROM [dbo].[Account] Account\n" +
                "INNER JOIN [Person].[Person] Person ON Person.[AccountID] = Account.[AccountID]\n" +
                "LEFT JOIN [Work].[Work] w1 ON w1.[WorkID] = Person.[MainWorkID]\n" +
                "LEFT JOIN [Work].[Work] w2 ON w2.[WorkID] = Person.[AdditionalWorkID]\n";
        String searchByLogin = "WHERE Account.[Login] LIKE ?";
        String searchByName = "WHERE Person.[Name] LIKE ?";
        String searchBySurname = "WHERE Person.[Surname] LIKE ?";
        String searchByWork = "WHERE w1.[Name] LIKE ? OR w2.[Name] LIKE ?";

        switch (searchBy) {
            case 1:
                search(searchTemplate + searchByLogin);
                break;
            case 2:
                search(searchTemplate + searchByName);
                break;
            case 3:
                search(searchTemplate + searchBySurname);
                break;
            case 5:
                search(searchTemplate + searchByWork);
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void search(String query) {
        PreparedStatement statement;
        statement = DBConnection.getInstance().prepareStatement(query);
        try {
            if (searchBy == 5) {
                statement.setString(1, "%" + "" + filter +  "%");
                statement.setString(2, "%" + "" + filter +  "%");
            }
            else {
                statement.setString(1, "%" + "" + filter +  "%");
            }
            ResultSet r = statement.executeQuery();
            Utility.printSearchUser(r);
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
