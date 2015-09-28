
import java.util.List;
import javax.swing.JProgressBar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inferno
 */
public class StartStopServices {

    public static final String START_SERVICES = "start";
    public static final String STOP_SERVICES = "stop";
    public static int ProcessedRecords=0;

    public static void startOrStopServices(List<String> listOfServices, String startOrStop,JProgressBar jProgressBar) {
        if ((listOfServices != null && startOrStop != null) && (startOrStop.equals(START_SERVICES) || startOrStop.equals(STOP_SERVICES))) {
            try {
                for (int i = 0; i < listOfServices.size(); i++) {
                    Process p = Runtime.getRuntime().exec("net " + startOrStop + " " + listOfServices.get(i));
                    p.waitFor();
                    p.getOutputStream().close();
                    p = null;
                    ProcessedRecords++;
                    updateProgressBar(jProgressBar);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                System.out.println("All Processed");
            }
        }
    }
    private static void updateProgressBar(JProgressBar jProgressBar)
    {
        jProgressBar.setValue(ProcessedRecords);
    }
}
