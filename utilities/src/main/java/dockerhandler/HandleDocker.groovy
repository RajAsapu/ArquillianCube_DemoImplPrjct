package dockerhandler

import com.google.common.base.Splitter
import org.apache.log4j.Logger

import java.util.regex.Pattern


class HandleDocker extends DockerCommands{
    private String output;
    private boolean isRunning;
    private Map<String,String> map;
    public  enum RContainer {price_ui,price_service}
    final static Logger logger = Logger.getLogger(HandleDocker.class.getName());
    public enum env{dev,test}

    private void isDockerInstalled()
    {
        output= checkVersion.execute().text

        if(output.contains("version")) {
            logger.info(output);
        }
        else {
            logger.info("Docker is not installed");
            assert false
        }
    }

    private void startDockerDaemon()
    {
        if(startDaemon.execute().alive) {
            logger.info("Docker started on default ip");
        }
        else{
            logger.info("Docker daemon is not running");
            assert false;
        }
    }

    /*
      Running Containers
     */
    private void runPriceUiImage()
    {
        isRunning=runPriceUiImage.execute().alive

        if(isRunning)
        {
            logger.info("Running Price Ui Container ...");
        }
        else
        {
            logger.info("Price Ui Container hasn't started...");
            assert false;
        }
    }
    public void runPriceServiceImage()
    {
        isRunning=runPriceServiceImage.execute().alive

        if(isRunning)
        {
            logger.info("Running Price Service Container ...");
        }
        else
        {
            logger.info("Price Service Container hasn't started...");
            assert false;
        }
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
            default: logger.info("Invalid container...");
        }

        output=command.execute().text.replaceAll("\"","").replaceAll(" ","").find(ip)
        output=output+" "+command.execute().text.replaceAll("\"","").replaceAll(" ","").find(port)

        map=Splitter.on(" ").omitEmptyStrings().withKeyValueSeparator(":").split(output)

        return map
    }

    private void tearDownDocker()
    {
        isRunning=stopDaemon.execute().alive
        if(isRunning)
            logger.info("Container has restarted...");
    }

    public void runDocker(env e)
    {
        switch (e)
        {
            case env.dev: logger.info("Running Dev Environment")
                           break
            case env.test: println "local"
        }
    }
    public void stopDocker()
    {
        tearDownDocker()
    }
}
