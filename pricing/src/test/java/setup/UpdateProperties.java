package setup;

import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostIp;
import org.arquillian.cube.HostPort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class UpdateProperties {

    public Properties props = new Properties();
    File resourceFile;
    String resourceFilePath;
    OutputStream out;

    public void setProperty(String ipproperty,String ip,String portproperty,String port) {

        resourceFile = new File(".//");
        resourceFilePath = resourceFile.getAbsolutePath().replace(".", "/src/test/resources/pricing.properties");
        resourceFile = new File(resourceFilePath);

        try {
            out = new FileOutputStream(resourceFile);
            props.setProperty(ipproperty, ip);
            props.setProperty(portproperty, port);

            props.store(out, ipproperty+" updated");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
