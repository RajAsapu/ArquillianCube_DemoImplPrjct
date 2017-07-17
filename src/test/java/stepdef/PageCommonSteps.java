package stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import functions.PageCommonMethods;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.*;

public class PageCommonSteps extends OpenBrowser {
    final static Logger logger = Logger.getLogger(PageCommonSteps.class.getName());
    private EventFiringWebDriver edriver;
    /*
     * Global variables
     */
    private PageFactory pageFactory = null;

    public PageCommonSteps() {
        pageFactory = new PageFactory();
    }

    @And("^wait for sometime$")
    public void wait_for_sometime() {
       // pageFactory.getPageCommonMethods().waitFor(1);
    }

    /*
     * Opens up the pricing application
     */
    @Given("^the user has logged into the pricing application$")
    public void the_user_has_logged_into_the_pricing_application() {
        initBrowser();
        pageFactory.getPageCommonMethods().login();
        edriver = DriverBean.getDriver();
    }

    /*
     * User clicks on the search button in the Index and Currency Exchange pages
     */
    @When("^clicks on the search button$")
    public void clicks_on_the_search_button() {
        WebElement search = edriver.findElement(By.xpath(Constants.indexCreate_search_xpath));
        if (!search.isEnabled()) {
            Assert.fail("Search Button is not enabled");
        }
        Actions actions = new Actions(edriver);
        actions.click(search).perform();
        wait_for_sometime();
    }

    /*
     * User clicks on the update button in workbook data
     */
    @When("^clicks on the update button$")
    public void clicks_on_the_update_button() {
        WebElement update = edriver.findElement(By.xpath(Constants.formulaList_updateAction_xpath));
        if (!update.isEnabled()) {
            Assert.fail("Update Button is not enabled");
        }
        Actions actions = new Actions(edriver);
        actions.click(update).perform();
        wait_for_sometime();
    }

    /*
     * Overloaded method: User clicks on the search button in the Index and Currency Exchange pages
     * Doesn't validate if button is enabled
     */
    @When("^search button is clicked$")
    public void search_button_is_clicked() {
        WebElement search = edriver.findElement(By.xpath(Constants.indexCreate_search_xpath));
        Actions actions = new Actions(edriver);
        actions.click(search).perform();
        wait_for_sometime();
    }

    /*
     * User clicks on the search button in the Index and Currency Exchange pages
     */
    @And("^clicks on the submit button$")
    public void clicks_on_the_submit_button() throws Exception {
        edriver.findElement(By.xpath(Constants.indexCreate_submit_xpath)).click();
        wait_for_sometime();
    }

    /*
    * User clicks on the save button in the Workbook data
    */
    @And("^clicks on the save button$")
    public void clicks_on_the_save_button() {
        edriver.findElement(By.xpath(Constants.workbookData_save_xpath)).click();
        wait_for_sometime();
    }

    /*
     * This method is used to navigate to List or Create pages under the Menus
     * (calculation,workbook,index,currency exchange or formula)
     */
    @Given("^the user has navigated to the \"([^\"]*)\" page under the \"([^\"]*)\"$")
    public void the_user_has_navigated_to_the_page_under_the(PageCommonMethods.page page, PageCommonMethods.module module)
             {
        pageFactory.getPageCommonMethods().moveTo(page, module);
    }
}