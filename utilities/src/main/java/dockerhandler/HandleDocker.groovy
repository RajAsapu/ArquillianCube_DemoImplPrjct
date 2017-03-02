package dockerhandler

import com.google.common.base.Splitter
import org.apache.log4j.Logger

import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


class HandleDocker extends DockerCommands{
    private String output;
    private boolean isRunning;
    private Map<String,String> map;
    public  enum RContainer {price_ui,price_service,price_datamock,price_engine,price_db}
    final static Logger logger = Logger.getLogger(HandleDocker.class.getName());
    public enum env{dev,test,mock}

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
        isRunning=runPriceUiImage.execute().waitFor(15,TimeUnit.SECONDS)

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
        isRunning=runPriceServiceImage.execute().waitFor(15,TimeUnit.SECONDS)

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
    public void runPriceServiceMockImage()
    {
        isRunning=runPriceServiceMockImage.execute().waitFor(15,TimeUnit.SECONDS)

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
    public void runPriceDBImage()
    {
        isRunning=runPriceDBImage.execute().waitFor(15,TimeUnit.SECONDS)

        if(isRunning)
        {
            logger.info("Running Price DB Container ...");
        }
        else
        {
            logger.info("Price DB Container hasn't started...");
            assert false;
        }
    }
    public void runPriceEngineImage()
    {
        isRunning=runPriceEngineImage.execute().waitFor(15,TimeUnit.SECONDS)

        if(isRunning)
        {
            logger.info("Running Price engine Container ...");
        }
        else
        {
            logger.info("Price engine Container hasn't started...");
            assert false;
        }
    }
    public void runPriceDataMockImage()
    {
        isRunning=runPriceDataMockImage.execute().waitFor(15,TimeUnit.SECONDS)

        if(isRunning)
        {
            logger.info("Running Price Data mock Container ...");
        }
        else
        {
            logger.info("Price Data mock Container hasn't started...");
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
            case RContainer.price_db: command = getIpAndHost_DB;
                break;
            case RContainer.price_datamock: command = getIpAndHost_DataMock;
                break;
            case RContainer.price_engine: command = getIpAndHost_Engine;
                break;

            default: logger.info("Invalid container...");
        }

        output=command.execute().text.replaceAll("\"","").replaceAll(" ","").find(ip)
        output=output+" "+command.execute().text.replaceAll("\"","").replaceAll(" ","").find(port)
        if(output!=null)
        {
            map=Splitter.on(" ").omitEmptyStrings().withKeyValueSeparator(":").split(output)
        }

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
            case env.test:
                           isDockerInstalled()
                           runPriceDBImage()
                           Thread.sleep(15000)
                           runPriceEngineImage()
                           Thread.sleep(15000)
                           runPriceUiImage()
                           Thread.sleep(15000)
                           runPriceServiceImage()
                           Thread.sleep(15000)
                           runPriceDataMockImage()
                           Thread.sleep(15000)
                           break
            case env.mock: isDockerInstalled()
                           runPriceServiceMockImage()
                            Thread.sleep(5000)
                           runPriceUiImage()
                            Thread.sleep(5000)
                            break
        }
    }
    public void stopDocker()
    {
        tearDownDocker()
    }
}
