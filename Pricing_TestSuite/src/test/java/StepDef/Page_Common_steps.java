package StepDef;

import Constants.Urls;
import DockerHandler.HandleDocker;
import Setup.BaseClass;
import Setup.CommonFn;
import Setup.Constants;
import Setup.DriverBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Page_Common_steps extends BaseClass {
    static EventFiringWebDriver edriver;
    final static Logger logger = Logger.getLogger(Page_Common_steps.class.getName());
    CommonFn fn;
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
        Map<String,String> map=dock.getIPandHostPort(HandleDocker.RContainer.price_ui);
        /*
          Load properties file
         */
        try {
            props.setProperty("pricingui.ipaddress","localhost");
            props.setProperty("pricingui.hostname","4200");
            File f=new File("Pricing.properties");
            OutputStream out=new FileOutputStream(f);
            props.store(out,"Updated pricing Ui params");
            logger.info("Updated pricing Ui params");

            map=dock.getIPandHostPort(HandleDocker.RContainer.price_service);
            props.setProperty("pricingservice.ipaddress",map.get("IPAddress"));
            props.setProperty("pricingservice.hostname",map.get("HostPort"));
            f=new File("Pricing.properties");
            out=new FileOutputStream(f);
            props.store(out,"Updated pricing service params");
            logger.info("Updated pricing service params");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
    @Given("^the docker containers are running$")
    public void the_docker_containers_are_running() {
        new HandleDocker().runDocker();
        updateProperties();
    }
    /*
      Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application(){
        edriver=initBrowser("http://"+props.getProperty("pricingui.ipaddress")+":"+props.getProperty("pricingui.hostname"));
        DriverBean.setDriver(edriver);

        fn=new CommonFn();
        fn.login();
    }
    /*
      User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button()throws Exception {
        edriver.findElement(By.xpath(Constants.index_search_xpath)).click();
        Thread.sleep(5000);
    }
    /*
      This method is used to navigate to List or Create pages under the Menus (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(String arg1, String arg2)throws Exception{
        fn.moveTo(CommonFn.module.Index, CommonFn.page.List);
    }
}
