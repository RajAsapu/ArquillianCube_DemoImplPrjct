package StepDef;

import Constants.Urls;
import Setup.BaseClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class Page_Common_steps extends BaseClass{
    public WebDriver driver=null;
    /*
      Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application(){
        driver=initBrowser(url.Pricing);
        driver.get(Urls.pricingUrl);
        System.out.println("Came here");
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
    public void the_user_has_navigated_to_the_page_under_the(String arg1, String arg2){
        System.out.println("Came here");
    }
}
