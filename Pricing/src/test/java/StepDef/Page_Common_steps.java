package StepDef;

import Constants.Urls;
import DockerHandler.HandleDocker;
import Setup.BaseClass;
import Setup.CommonFn;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Page_Common_steps extends CommonFn{
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
        Map<String,String> map=dock.getIPandHostPort();
        /*
          Load properties file
         */
        try {
            props.setProperty("pricingui.ipaddress",map.get("IPAddress"));
            props.setProperty("pricingui.hostname",map.get("HostPort"));
            File f=new File("Pricing.properties");
            OutputStream out=new FileOutputStream(f);
            props.store(out,"Updated Pricing Ui params");

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
        driver=initBrowser("http://"+props.getProperty("pricingui.ipaddress"));
    }
    /*
      User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button() {
            System.out.println("Came here");

    }
    /*
      This method is used to navigate to List or Create pages under the Menus (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(String arg1, String arg2)throws Exception{
        moveTo(module.Index, page.List);
//        Thread.sleep(3000);
    }
}
