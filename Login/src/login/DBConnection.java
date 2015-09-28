/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Inferno
 */
public class DBConnection {
    
    public static Connection dbConnection() {
        try
        {
        Connection con = DriverManager.getConnection("jdbc:sybase:Tds:Inferno-PC:5000/master", "sa", "Infosys123");
        return con;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
