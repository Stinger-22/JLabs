package com.labs.complex.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utility {
    public static void printSearchUser(ResultSet r) throws SQLException {
        for (int i = 1; r.next(); i++) {
            System.out.printf("N:%d\nAccountID: %d\nLogin: %s\nPassword: %s\nName: %s\nSurname: %s\nSalary: %.2f\n" +
                            "Kids: %d\nMain work: %s\nAdditional work: %s\n\n\n", i, r.getInt(1),
                    r.getString(2), r.getString(3),
                    r.getString(7), r.getString(8),
                    r.getDouble(9), r.getInt(10),
                    r.getString(14), r.getString(17)
            );
        }
    }
}
