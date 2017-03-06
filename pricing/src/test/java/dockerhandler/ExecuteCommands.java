package dockerhandler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommands {

    public static boolean exec(String command)
    {
        String temp=null;

        try {
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((temp = stdInput.readLine()) != null) {
                System.out.println(temp);
            }

            while ((temp = stdError.readLine()) != null) {
                System.out.println(temp);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
