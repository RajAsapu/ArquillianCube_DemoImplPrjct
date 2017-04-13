package setup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class UpdateProperties {

    public Properties props = new Properties();
    File resourceFile;
    String resourceFilePath;
    OutputStream out;

    public void setProperty(Map<String,String> map) {

        try{
        resourceFile = new File(".//");
        resourceFilePath = resourceFile.getAbsolutePath().replace(".", "/src/test/resources/pricing.properties");
        resourceFile = new File(resourceFilePath);
        out = new FileOutputStream(resourceFile);
        for(Map.Entry<String,String> entry:map.entrySet())
        {
            props.setProperty(entry.getKey(), entry.getValue());
        }
            props.store(out, "Container Properties");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
