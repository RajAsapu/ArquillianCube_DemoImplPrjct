package DockerHandler

class DockerCommands {

    public static String checkVersion    = "docker -v"
    public static String startDaemon     = "service docker start"
    public static String stopDaemon      = "service docker restart"
    /*
      Need to build the image  by pulling the code from git repo
     */
    public static String runPriceUiImage = "docker start 10bc174c9c03"
    public static String getIpAndHost    = "docker inspect 10bc174c9c03 | grep IPAddress"//|awk '/IPAddress/ || /HostPort/'"
}
