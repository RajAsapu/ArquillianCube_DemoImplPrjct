package artifacts;

import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import static io.restassured.RestAssured.when;
import static io.restassured.path.xml.XmlPath.from;

public class GetArtifacts {

    InputStream in;
    Properties props;
    String bambooRestEndPoint,bambooBuidNumber ;
    Response response;
    List<String> artifactUrl;

    public void getArtifactsService()throws IOException
    {
        props=new Properties();
        in=getClass().getClassLoader().getResourceAsStream("repos.properties");
        props.load(in);
        bambooRestEndPoint = props.getProperty("bambooAws_restEndPoint");
        bambooBuidNumber = props.getProperty("bambooAws_serviceBuildNumber");
        response = when().get(bambooRestEndPoint+"/ENTERPRISEPRICEENGINE-PRICECONFIGURATIONSERVICE-JOB1-"+bambooBuidNumber+"?buildState=Successful&expand=artifacts");
        artifactUrl = from(response.getBody().asInputStream()).getList("result.artifacts.artifact.link.@href");

        for(String url:artifactUrl)
        {
            if(url.contains("artifact"))
            {
                String fileName;
                if(url.contains("jar"))
                {
                    fileName = "./Artifacts/price-configuration-service-1.0-SNAPSHOT.jar";
                }
                else
                {
                    String[] path=url.split("/");
                    fileName= "./Artifacts/"+path[path.length-1];
                }
                FileUtils.copyURLToFile(new URL(url),new File(fileName));
            }
        }
    }
    public void getArtifactsUi()throws IOException
    {
        props=new Properties();
        in=getClass().getClassLoader().getResourceAsStream("repos.properties");
        props.load(in);
        bambooRestEndPoint = props.getProperty("bambooAws_restEndPoint");
        bambooBuidNumber = props.getProperty("bambooAws_uiBuildNumber");
        response = when().get(bambooRestEndPoint+"/ENTERPRISEPRICEENGINE-PRICECONFIGURATIONUI-JOB1-"+bambooBuidNumber+"?expand=artifacts");
        artifactUrl = from(response.getBody().asInputStream()).getList("result.artifacts.artifact.link.@href");

        for(String url:artifactUrl)
        {
            if(url.contains("artifact"))
            {
                String[] path=url.split("/");
                String fileName= "./Artifacts/"+path[path.length-1];
                FileUtils.copyURLToFile(new URL(url),new File(fileName),60000,60000);
            }
        }
    }
    public void getArtifactsDataMock()throws IOException
    {
        props=new Properties();
        in=getClass().getClassLoader().getResourceAsStream("repos.properties");
        props.load(in);
        bambooRestEndPoint = props.getProperty("bambooAws_restEndPoint");
        bambooBuidNumber = props.getProperty("bambooAws_uiBuildNumber");
        response = when().get(bambooRestEndPoint+"/ENTERPRISEPRICEENGINE-PRICECONFIGURATIONSERVICE-JOB1-"+bambooBuidNumber+"?expand=artifacts");
        artifactUrl = from(response.getBody().asInputStream()).getList("result.artifacts.artifact.link.@href");

        for(String url:artifactUrl)
        {
            if(url.contains("artifact"))
            {
                String fileName;
                if(url.contains("jar"))
                {
                    fileName = "./Artifacts/price-configuration-service-1.0-SNAPSHOT.jar";
                }
                else
                {
                    String[] path=url.split("/");
                    fileName= "./Artifacts/"+path[path.length-1];
                }
                FileUtils.copyURLToFile(new URL(url),new File(fileName));
            }
        }
    }
}
