
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inferno
 */
public class DbConnection {

    private static Connection connection = null;

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            return connection;
        } else {
            File file =  new File (".");
            try {
                System.err.println("jgjhg  "+file.getCanonicalFile());
            } catch (IOException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return DriverManager.getConnection("jdbc:sqlite:prglist.db");
        }
    }
}
