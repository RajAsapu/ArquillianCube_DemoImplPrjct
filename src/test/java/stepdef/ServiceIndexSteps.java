package stepdef;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.DriverBean;
import setup.PageFactory;

import java.util.List;

public class ServiceIndexSteps extends AbstractApiDefinitions {

    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private EventFiringWebDriver edriver;
    private PageFactory pageFactory = null;
    private Response response;
    private ResponseBody body;

    public ServiceIndexSteps() {
        pageFactory = new PageFactory();
        edriver = DriverBean.getDriver();
    }

    @When("^the user querys the GET on search end point in list page with parameters as \"([^\"]*)\"$")
    public void the_user_querys_the_GET_on_search_end_point_in_list_page(List<String> args) throws Throwable {
        response = spec().given()
                .get("/index/?startDate=" + args.get(0) + "&status=" + args.get(1) + "&type=" + args.get(2)
                        + "&rateBasis=" + args.get(3) + "&name=" + args.get(4) + "&currency=" + args.get(5) + "&uom="
                        + args.get(6) + "&startIndex=0&limit=15");
    }

    @When("^the user querys POST method on create index end point( with parameters as \"(.*?)\" |)$")
    public void the_user_querys_POST_method_on_create_index_end_point(DataTable table) throws Throwable {
        List<List<String>> row = table.raw();
        /*
         * Check if we have a way to get by column index
		 */
        response = spec().given()
                .body("{\"endDate\":null," + "\"date\":\"" + row.get(1).get(1) + "\"," + "\"code\":\"XNHO001\","
                        + "\"low\":\"" + row.get(1).get(0) + "\"," + "\"mid\":\"" + row.get(1).get(1) + "\","
                        + "\"high\":\"" + row.get(1).get(2) + "\"," + "\"close\":" + row.get(1).get(10) + ","
                        + "\"name\":\"" + row.get(1).get(4) + "\"," + "\"currency\":\"" + row.get(1).get(7) + "\","
                        + "\"uom\":\"" + row.get(1).get(8) + "\"," + "\"rateBasis\":\""
                        + row.get(1).get(3).toUpperCase() + "\"," + "\"comments\":\"" + row.get(1).get(9) + "\","
                        + "\"scaleRates\":null}")
                .contentType(ContentType.JSON).post("/index/");
    }

    @When("^the response from the service is captured$")
    public void the_response_from_the_service_is_captured() {
        body = response.getBody();
        logger.info("****");
        body.prettyPrint();
    }

    @Then("^the service should return a successful response$")
    public void the_service_should_return_a_successful_response() throws Throwable {
        Verify.verify(response.statusCode() == 200, "Response is not successful:" + response.getStatusCode());
    }

    @Then("^the service should return an unsuccessful response$")
    public void the_service_should_return_an_unsuccessful_response() throws Throwable {
        Verify.verify(response.statusCode() != 200, "Response is Successful:" + response.getStatusCode());
        logger.info(body.jsonPath().prettyPrint());
    }
}
