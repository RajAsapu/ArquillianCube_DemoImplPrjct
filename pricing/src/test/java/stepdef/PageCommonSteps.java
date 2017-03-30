package stepdef;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import dockerhandler.HandleDocker;
import setup.BaseClass;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;

public class PageCommonSteps extends BaseClass {
	final static Logger logger = Logger.getLogger(PageCommonSteps.class.getName());
	static EventFiringWebDriver edriver;
	/*
	 * Global variables
	 */
	public Properties props = new Properties();
	CommonFunctions fn;
	HandleDocker dock = new HandleDocker();
	Map<String, String> map;
	File resourceFile;
	String resourceFilePath;
	OutputStream out;
	/*
	 * Update Pricing.properties file with ip address and host port
	 */
	public void updateProperties() {
		/*
		 * Load properties file
		 */
		resourceFile = new File(".//");
		resourceFilePath = resourceFile.getAbsolutePath().replace(".", "pricing/src/test/resources/pricing.properties");
		resourceFile = new File(resourceFilePath);

		try {
			out = new FileOutputStream(resourceFile);
			props.setProperty("pricingui.ipaddress", "localhost");
			props.setProperty("pricingui.hostname", "4200");

//			map = dock.getIPandHostPort(HandleDocker.RContainer.price_service);
//			props.setProperty("pricingservice.ipaddress", map.get("IPAddress"));
//			props.setProperty("pricingservice.hostname", map.get("HostPort"));
//
//			map = dock.getIPandHostPort(HandleDocker.RContainer.price_db);
//			props.setProperty("pricingdb.ipaddress", map.get("IPAddress"));
//			props.setProperty("pricingdb.hostname", map.get("HostPort"));
//
//			map = dock.getIPandHostPort(HandleDocker.RContainer.price_datamock);
//			props.setProperty("pricingdatamock.ipaddress", map.get("IPAddress"));
//			props.setProperty("pricingdatamock.hostname", map.get("HostPort"));
//
//			map = dock.getIPandHostPort(HandleDocker.RContainer.price_engine);
//			props.setProperty("pricingengine.ipaddress", map.get("IPAddress"));
//			props.setProperty("pricingengine.hostname", map.get("HostPort"));

			props.store(out,"*IP and HostPort of active containers*");
			logger.info("Updated pricing properties");
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	@Given("^the docker containers are running$")
	public void the_docker_containers_are_running() {
		new HandleDocker().runDocker(HandleDocker.env.test);
		updateProperties();
	}
	@Given("^the docker mock containers are running$")
	public void the_docker_mock_containers_are_running() {
		new HandleDocker().runDocker(HandleDocker.env.mock);
		updateProperties();
	}
	/*
	 * Opens up the pricing application
	 */
	@Given("^the user has logged into the pricing application$")
	public void the_user_has_logged_into_the_pricing_application() {
		edriver = initBrowser(
				"http://" + props.getProperty("pricingui.ipaddress") + ":" + props.getProperty("pricingui.hostname"));
		DriverBean.setDriver(edriver);

		fn = new CommonFunctions();
		fn.login();
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
	public void the_user_has_navigated_to_the_page_under_the(CommonFunctions.page page, CommonFunctions.module module)
			throws Exception {
		fn.moveTo(module, page);
	}
}
