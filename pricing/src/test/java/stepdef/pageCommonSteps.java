package stepdef;

import dockerhandler.handleDocker;
import setup.baseClass;
import setup.commonFunctions;
import setup.constants;
import setup.driverBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class pageCommonSteps extends baseClass {
    static EventFiringWebDriver edriver;
    final static Logger logger = Logger.getLogger(pageCommonSteps.class.getName());
    commonFunctions fn;
    /*
      Global variables
     */
    public Properties props=new Properties();
    handleDocker dock=new handleDocker();
    /*
      Update Pricing.properties file with ip address and host port
     */
    public void updateProperties()
    {
        Map<String,String> map ;
        File resourceFile;
        String resourceFilePath;
        OutputStream out;
        /*
          Load properties file
         */
        resourceFile=new File(".//");
        resourceFilePath=resourceFile.getAbsolutePath().replace(".","pricing/src/test/resources/pricing.properties");
        resourceFile=new File(resourceFilePath);

        try {
            out=new FileOutputStream(resourceFile);
            props.setProperty("pricingui.ipaddress","localhost");
            props.setProperty("pricingui.hostname","4200");

            map=dock.getIPandHostPort(handleDocker.RContainer.price_service);
            props.setProperty("pricingservice.ipaddress",map.get("IPAddress"));
            props.setProperty("pricingservice.hostname",map.get("HostPort"));
            props.store(out,"Ip address and host port are updated");
            logger.info("Updated pricing properties");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
    @Given("^the docker containers are running$")
    public void the_docker_containers_are_running() {
        new handleDocker().runDocker();
        updateProperties();
    }
    /*
      Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application(){
        edriver=initBrowser("http://"+props.getProperty("pricingui.ipaddress")+":"+props.getProperty("pricingui.hostname"));
        driverBean.setDriver(edriver);

        fn=new commonFunctions();
        fn.login();
    }
    /*
      User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button()throws Exception {
        edriver.findElement(By.xpath(constants.indexList_search_xpath)).click();
        Thread.sleep(5000);
    }
    /*
      This method is used to navigate to List or Create pages under the Menus (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(String arg1, String arg2)throws Exception{
        fn.moveTo(commonFunctions.module.Index, commonFunctions.page.List);
    }
}
