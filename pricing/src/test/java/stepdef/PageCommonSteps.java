package stepdef;

import cucumber.api.java.en.And;
import dockerhandler.HandleDocker;
import setup.BaseClass;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PageCommonSteps extends BaseClass {
    static EventFiringWebDriver edriver;
    final static Logger logger = Logger.getLogger(PageCommonSteps.class.getName());
    CommonFunctions fn;
    /*
      Global variables
     */
    public Properties props=new Properties();
    HandleDocker dock=new HandleDocker();
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

            map=dock.getIPandHostPort(HandleDocker.RContainer.price_service);
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
        new HandleDocker().runDocker(HandleDocker.env.dev);
        updateProperties();
    }
    /*
      Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application(){
        edriver=initBrowser("http://"+props.getProperty("pricingui.ipaddress")+":"+props.getProperty("pricingui.hostname"));
        DriverBean.setDriver(edriver);

        fn=new CommonFunctions();
        fn.login();
    }
    /*
      User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button() {
        edriver.findElement(By.xpath(Constants.indexList_search_xpath)).click();
    }
    /*
    User clicks on the search button in the Index and Currency Exchange pages
   */
    @And("^clicks on the submit button$")
    public void clicks_on_the_submit_button() {
        edriver.findElement(By.xpath(Constants.indexCreate_submit_xpath)).click();
    }
    /*
      This method is used to navigate to List or Create pages under the Menus (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(String page, String module)throws Exception{
        if(page.equals("List"))
            fn.moveTo(CommonFunctions.module.Index, CommonFunctions.page.List);
        else if(page.equals("Create"))
            fn.moveTo(CommonFunctions.module.Index, CommonFunctions.page.Create);
    }
}
