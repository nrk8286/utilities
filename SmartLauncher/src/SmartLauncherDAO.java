
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inferno
 */
public class SmartLauncherDAO {

    public static List getProgramListFromDB() throws SQLException {
        List<Program> list = new ArrayList<Program>();
        Statement st = DbConnection.getConnection().createStatement();
        String query = "select processname,processlocation from tb_prglist";
        ResultSet rs = st.executeQuery(query);
        Program p =null;
        while (rs.next()) {
            p= new Program();
            p.setProgramName(rs.getString(1));
            p.setProgramLocation(rs.getString(2));
            list.add(p);
        }
        return list;
    }
    public static void insertApplication(String processName,String processLocation) throws SQLException
    {
        Statement st = DbConnection.getConnection().createStatement();
        String query = "insert into "
                + "tb_prglist(processname,processlocation) values ('"+processName+ "','"+processLocation+"')";
        int i = st.executeUpdate(query);
        st.close();
        System.out.println("Record Insert "+i);
    }
    public static void deleteAll() throws SQLException
    {
        Statement st = DbConnection.getConnection().createStatement();
        String query = "delete from tb_prglist";
        int i = st.executeUpdate(query);
        st.close();
        System.out.println("Record Deleted "+i);
    }
}
