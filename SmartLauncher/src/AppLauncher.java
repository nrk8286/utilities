
import java.io.IOException;
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
public class AppLauncher {
    
    public static void launchApplication(String  appPath)
    {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c start  "+appPath);
        } catch (IOException ex) {
            Logger.getLogger(AppLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
