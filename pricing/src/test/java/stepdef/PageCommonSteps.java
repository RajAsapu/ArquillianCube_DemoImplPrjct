package stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import functions.PageCommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.*;

public class PageCommonSteps extends BaseClass {
    final static Logger logger = Logger.getLogger(PageCommonSteps.class.getName());
    static EventFiringWebDriver edriver;
    /*
     * Global variables
     */
    private PageFactory pageFactory = null;
    private UpdateProperties updateProperties;
    private String environment = null;

    public PageCommonSteps()
    {
        updateProperties = new UpdateProperties();
        environment = updateProperties.getEnv();
        pageFactory = new PageFactory();
    }

    @And("^wait for sometime$")
    public void wait_for_sometime() throws Exception {
        Thread.sleep(10000);
    }

    /*
     * Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application() {

        if (environment.equalsIgnoreCase("Docker")) {
            edriver = initBrowser(updateProperties.getProperty("pricing.ui"));
        } else if (environment.equalsIgnoreCase("Test")){
            edriver = initBrowser("https://epe-priceconfig-ui.test.aws.wfscorp.com");
        } else if(environment.equalsIgnoreCase("Dev")){
            edriver = initBrowser("https://epe-priceconfig-ui.dev.aws.wfscorp.com");
        }
        DriverBean.setDriver(edriver);
        pageFactory.getPageCommonMethods().login();
    }

    /*
     * User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button() {
        edriver.findElement(By.xpath(Constants.indexList_search_xpath)).click();
    }

    /*
     * User clicks on the search button in the Index and Currency Exchange pages
     */
    @And("^clicks on the submit button$")
    public void clicks_on_the_submit_button() throws Exception {
        edriver.findElement(By.xpath(Constants.indexCreate_submit_xpath)).click();
        Thread.sleep(3000);
    }

    /*
     * This method is used to navigate to List or Create pages under the Menus
     * (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(PageCommonMethods.page page, PageCommonMethods.module module)
            throws Exception {
        pageFactory.getPageCommonMethods().moveTo(page, module);
    }
}
