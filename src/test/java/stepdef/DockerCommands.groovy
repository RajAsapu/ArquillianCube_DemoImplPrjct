package stepdef

import org.apache.log4j.Logger

/**
 * Created by root on 6/1/17.
 */
class DockerCommands {
    final static Logger logger = Logger.getLogger(DockerCommands.class.getName());
    public static void ps()
    {
        logger.debug("docker ps".execute().text);
    }
    public static void images()
    {
        logger.debug("docker images".execute().text);
    }
    public static void psa()
    {
        logger.debug("docker ps -a".execute().text);
    }
}
