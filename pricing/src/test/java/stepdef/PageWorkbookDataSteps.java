package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import setup.PageFactory;

public class PageWorkbookDataSteps {
    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    String temp;
    private PageFactory pageFactory;

    public PageWorkbookDataSteps() {
        pageFactory = new PageFactory();
    }

    @And("^set the start date for data as \"([^\"]*)\"$")
    public void set_the_start_date_for_data_as(String startDate) {
        pageFactory.getWorkBookDataMethods().setStartDate(startDate);
    }

    @And("^set the end date for data as \"([^\"]*)\"$")
    public void set_the_end_date_for_data_as(String endDate) {
        pageFactory.getWorkBookDataMethods().setEndDate(endDate);
    }

    @Then("^the workbook data (should|should not) be (created|dispalyed in the search results)$")
    public void the_workbook_data_should_be_created(String cond,String placeHolder, DataTable table) throws Exception {
        if (cond.contains("not")) {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(false,table);
        } else {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(true,table);
        }
    }

    @And("^(supplier|supplierSite|location|item|hauler|customerShipto|customer|fbo|price basis|uom|set the currency for data|set the amount for data) as \"([^\"]*)\"$")
    public void attribute_as_attribute(String attribute,String attributeValue)
    {
        if(!attributeValue.equals(null) && !attributeValue.equals(""))
        {
            pageFactory.getWorkBookDataMethods().setAttribute(attribute,attributeValue);
        }
    }
}
