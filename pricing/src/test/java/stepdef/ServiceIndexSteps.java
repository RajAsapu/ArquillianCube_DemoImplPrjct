package stepdef;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;
import setup.ServiceEndPoints;

import java.util.List;

import static io.restassured.RestAssured.when;

public class ServiceIndexSteps extends AbstractApiDefinitions{

    private CommonFunctions fn=new CommonFunctions();
    private static EventFiringWebDriver edriver= DriverBean.getDriver();
    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    public PageCommonSteps steps=new PageCommonSteps();
    private Response response;
    private ResponseBody body;

    @When("^the user querys the GET on search end point in list page with parameters as \"([^\"]*)\"$")
    public void the_user_querys_the_GET_on_search_end_point_in_list_page(List<String> args) throws Throwable {
        response=spec().given().get("/index/?startDate="+args.get(0)+
                                        "&status="+args.get(1)
                                        +"&type="+args.get(2)
                                        +"&rateBasis="+args.get(3)
                                        +"&name="+args.get(4)
                                        +"&currency="+args.get(5)
                                        +"&uom="+args.get(6)
                                        +"&startIndex=0&limit=15");
    }

    @When("^the user querys POST method on create index end point( with parameters as \"(.*?)\" |)$")
    public void the_user_querys_POST_method_on_create_index_end_point(DataTable table)throws Throwable
    {
        List<List<String>> row=table.raw();

//        for(List<String> row:params)
//        {
        /*
          Check if we have a way to get by column index
         */
            response=spec().given().body("{\"endDate\":null," +
                    "\"date\":\""+row.get(1).get(1)+"\"," +
                    "\"code\":\"XNHO001\"," +
                    "\"low\":\""+row.get(1).get(0)+"\"," +
                    "\"mid\":\""+row.get(1).get(1)+"\"," +
                    "\"high\":\""+row.get(1).get(2)+"\"," +
                    "\"close\":"+row.get(1).get(10)+"," +
                    "\"name\":\""+row.get(1).get(4)+"\"," +
                    "\"currency\":\""+row.get(1).get(7)+"\"," +
                    "\"uom\":\""+row.get(1).get(8)+"\"," +
                    "\"rateBasis\":\""+row.get(1).get(3).toUpperCase()+"\"," +
                    "\"comments\":\""+row.get(1).get(9)+"\"," +
                    "\"scaleRates\":null}")
                    .contentType(ContentType.JSON)
                    .post("/index/");
//        }

//        response=spec().given().body("{\"endDate\":\""+params[6]
//                                        +"\",\"date\":\""+params[5]
//                                        +"\",\"code\":\"XNRB001\",\"low\":\""+params[0]
//                                        +"\",\"mid\":\""+params[1]
//                                        +"\",\"high\":\""+params[2]
//                                        +"\",\"close\":"+params[10]
//                                        +",\"name\":\""+params[4]
//                                        +"\",\"currency\":\""+params[7]
//                                        +"\",\"uom\":\""+params[8]
//                                        +"\",\"rateBasis\":\"" +params[3]
//                                        +"\",\"comments\":\""+params[9]
//                                        +"\",\"scaleRates\":null}")
//        response=spec().given().body("{\n" +
//                "  \"active\": true,\n" +
//                "  \"close\": "+params[10]+",\n" +
//                "  \"code\": \"XNRB001\",\n" +
//                "  \"comments\": \""+params[9]+"\",\n" +
//                "  \"currency\": \""+params[7]+"\",\n" +
//                "  \"date\":\""+params[5]+"\" ,\n" +
//                "  \"endDate\":\"" + params[6] + "\" ,\n" +
//                "  \"high\": "+params[2]+",\n" +
//                "  \"low\": "+params[0]+",\n" +
//                "  \"mid\": "+params[1]+",\n" +
//                "  \"name\": \""+params[4]+"\",\n" +
//                "  \"rateBasis\": \""+params[3]+"\",\n" +
//                "  \"scaleRates\": [\n" +
//                "    {\n" +
//                "      \"from\": 1,\n" +
//                "      \"rate\": 1,\n" +
//                "      \"to\": 1\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"type\": \"MANUAL\",\n" +
//                "  \"unit\": 0,\n" +
//                "  \"uom\": \""+params[8]+"\"\n" +
//                "}")
//                .contentType(ContentType.JSON)
//                .post("https://epe-priceconfig-s.dev.aws.wfscorp.com/index/");
    }
    @When("^the response from the service is captured$")
    public void the_response_from_the_service_is_captured()
    {
        body=response.getBody();
        logger.info("****");
        body.prettyPrint();
    }
    @Then("^the service should return a successful response$")
    public void the_service_should_return_a_successful_response() throws Throwable {
        Verify.verify(response.statusCode()==200,"Response is not successful:"+response.getStatusCode());
    }

    @Then("^the service should return an unsuccessful response$")
    public void the_service_should_return_an_unsuccessful_response() throws Throwable {
        Verify.verify(response.statusCode()!=200,"Response is Successful:"+response.getStatusCode());
        logger.info("******"+body.print());
        logger.info(body.jsonPath().prettyPrint());
        // Verify.verify(body.jsonPath().get("message").equals("The index with this name, code and type already exist"),"Duplicate of the record is inserted");
    }
}
