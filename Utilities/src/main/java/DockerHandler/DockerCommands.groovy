package DockerHandler

class DockerCommands {

    public static String checkVersion    = "docker -v"
    public static String startDaemon     = "service docker start"
    public static String stopDaemon      = "service docker restart"
    /*
      Need to build the image  by pulling the code from git repo
     */
    public static String runPriceUiImage = "docker start runPriceUi"
    public static String getIpAndHost_Ui    = "docker inspect runPriceUi"
    /*
      Need to build the images by pulling the code from git repo
     */
     public static String runPriceServiceImage = "docker start runPriceService"
     public static String getIpAndHost_Service = "docker inspect runPriceService"
}
