package dockerhandler;

public class DockerCommands {
    public static String checkVersion    = "docker -v";
    public static String startDaemon     = "service docker start";
    public static String stopDaemon      = "service docker restart";
    /*
      Need to build the image  by pulling the code from git repo
     */
    //Price Configuration Ui
    public static String runPriceUiImage = "docker start runPriceUi2.0";
    public static String getIpAndHost_Ui    = "docker inspect runPriceUi2.0";
    //Price Configuration Service
    public static String runPriceServiceImage = "docker start runPriceService3.0";
    public static String getIpAndHost_Service = "docker inspect runPriceService3.0";
    //Price Configuration DB
    public static String runPriceDBImage = "docker start runPriceDB4.0";
    public static String getIpAndHost_DB = "docker inspect runPriceDB4.0";
    //Price Engine
    public static String runPriceEngineImage = "docker start runPriceEngine";
    public static String getIpAndHost_Engine = "docker inspect runPriceEngine";
    //Price Data mock
    public static String runPriceDataMockImage = "docker start runDataMock4.0";
    public static String getIpAndHost_DataMock = "docker inspect runDataMock4.0";
    //Price Service Mock
    public static String runPriceServiceMockImage = "docker start runPriceServiceMock1.0";
    public static String getIpAndHost_ServiceMock    = "docker inspect runPriceServiceMock1.0";
}
