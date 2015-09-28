
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Description
 *
 * @author Inferno
 */
public class PowerShellAndCSVParser {

    //private final static String serviceListFile = "build/classes/resources/servicelist/serviceListDB.txt";
    private final static String serviceListFile = "serviceListDB.txt";
    private static int progressBarCounter;

    public static void main(String[] args) {
        getListOfOSServices();
    }

    /**
     * This method returns all the running and nit started services installed in
     * the windows OS.It is not supported for Linux yet.
     *
     * @return - Returns a list of services .
     */
    public static List<String[]> getListOfOSServices() {
        List<String[]> finalList = new ArrayList<>();
        if (!System.getProperty("os.name").contains("Win")) {
            return null;
        }

        File file = null;
        try {
            //Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\WindowsPowerShell\\v1.0\\" + "powershell.exe  Get-service | Format-List name , status , displayName");
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\WindowsPowerShell\\v1.0\\" + "powershell.exe  Get-service | Format-List name , status , displayName >" + serviceListFile);
            PrintStream ps = new PrintStream(p.getOutputStream());
            System.setOut(ps);
            p.getOutputStream().close();
            //Thread.sleep(2000);
            file = new File(serviceListFile);
            while(!file.exists())
            {
                //Thread.sleep(100);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        finalList = getCSVFromRAWList(file);
        return finalList;
    }

    private static List<String[]> getCSVFromRAWList(File file) {
        List<String[]> finalList = new ArrayList<>();
        List<String> tempList = convertToCSV(file);
        String[] temArray = null;
        for (int i = 0; i < tempList.size(); i++) {
            temArray = tempList.get(i).split(",");
            finalList.add(temArray);
            temArray = null;
        }
        return finalList;
    }

    private static List<String> convertToCSV(File file) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        List finalList = new ArrayList();
        try {
            System.out.println("Blaaaaa " + file.getCanonicalPath());
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getCanonicalPath()), "UTF-16"));
            //bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\csvfile.txt")));
            String line = "";
            String singleService = "";
            while ((line = bufferedReader.readLine()) != null) {
                if ((line.contains("Name        :") || line.contains("Status      :") || line.contains("DisplayName :"))) {
                    singleService = singleService + line.substring(14) + ",";
                } else {
                    if (singleService.length() != 0) {
                        //bufferedWriter.write(singleService);
                        finalList.add(singleService);
                        singleService = "";
                    }
                }
            }
            return finalList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return finalList;
    }
}
