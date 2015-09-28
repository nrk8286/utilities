/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Inferno
 */
public class LoginValidator {

    public static boolean checkRecord(String userName, String passWord) {
        try {
            Statement st = DBConnection.dbConnection().createStatement();
            String sql = "Select * from LoginDemo where username='" + userName + "' and password='" + passWord + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
