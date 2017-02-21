package DockerHandler

import com.google.common.base.Splitter
import org.junit.Before
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.util.Map
import java.util.regex.Pattern;

class HandleDocker extends DockerCommands{

    private String output;
    private Map<String,String> map;
    public  enum RContainer {price_ui,price_service}

    private void isDockerInstalled()
    {
        output= checkVersion.execute().text

        if(output.contains("version")) {
            println output
        }
        else {
            println "Docker is not installed"
            assert false
        }
    }

    private void startDockerDaemon()
    {
        if(startDaemon.execute().alive) {
            println "Docker started on default ip"
        }
        else{
            println "Docker daemon is not running"
        }
    }

    /*
      Running Containers
     */
    private void runPriceUiImage()
    {
        output=runPriceUiImage.execute().text

        if(output.length()>5)
            println "Running Price Ui Image ..."
    }
    public void runPriceServiceImage()
    {
        output=runPriceServiceImage.execute().text

        if(output.length()>5)
            println "Running Price Service Image ..."
    }
    /*
      Retrieve Ip Address and Host port for a container
     */
    public Map<String,String> getIPandHostPort(RContainer run)
    {
        String command;

        Pattern ip   = ~/IPAddress:[0-9.]*/;
        Pattern port = ~/HostPort:[0-9]*/;

        switch(run)
        {
            case RContainer.price_ui: command = getIpAndHost_Ui;
                break;
            case RContainer.price_service: command = getIpAndHost_Service;
                break;
            default: println "Invalid container"
        }

        output=command.execute().text.replaceAll("\"","").replaceAll(" ","").find(ip)
        output=output+" "+command.execute().text.replaceAll("\"","").replaceAll(" ","").find(port)

        map=Splitter.on(" ").omitEmptyStrings().withKeyValueSeparator(":").split(output)

        return map
    }

    private void tearDownDocker()
    {
        output=stopDaemon.execute().alive
        println output
    }

    public void runDocker()
    {
        // Check if the docker is installed
        isDockerInstalled()
        // Start the docker daemon
        startDockerDaemon()
        // Run the price ui image
        runPriceUiImage()
        // Run the price service image
        runPriceServiceImage()
    }
    public void stopDocker()
    {
        tearDownDocker()
    }

}
