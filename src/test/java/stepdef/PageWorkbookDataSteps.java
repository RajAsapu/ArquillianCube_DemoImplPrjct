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

    @And("^the user sets start date for the workbook data as ([^\"]*)$")
    public void set_the_start_date_for_data_as(String startDate) {
        pageFactory.getWorkBookDataMethods().setStartDate(startDate);
    }

    @And("^the user sets end date for the workbook data as ([^\"]*)$")
    public void set_the_end_date_for_data_as(String endDate) {
        pageFactory.getWorkBookDataMethods().setEndDate(endDate);
    }

    @Then("^the workbook data (should|should not) be (created|dispalyed in the search results)$")
    public void the_workbook_data_should_be_created(String cond, String placeHolder, DataTable table) throws Exception {
        if (cond.contains("not")) {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(false, table);
        } else {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(true, table);
        }
    }

    @And("^the user sets (supplier|supplierSite|location|item|hauler|customerShipto|customer|fbo|priceBasis|uom|currency|amount) for the workbook data as ([^\"]*)$")
    public void attribute_as_attribute(String attribute, String attributeValue) {
        if (!attributeValue.equals(null) && !attributeValue.isEmpty()) {
            pageFactory.getWorkBookDataMethods().setAttribute(attribute, attributeValue);
        }
    }

    @And("^the user creates the workbook data$")
    public void create_the_work_data(DataTable table) {
        pageFactory.getWorkBookDataMethods().createData(table);
    }

    @And("^the user edits the record with the supplier name as ([^\"]*)")
    public void edit_the_record_with_the_supplier_name_as(String supplierName) {
        pageFactory.getWorkBookDataMethods().clickOnDataWithSupplier(supplierName);
        pageFactory.getWorkBookDataMethods().clickOnUpdate();
    }

    @And("the user sets search status to \"([^\"]*)\"")
    public void the_user_sets_search_status_to(String status) {
        pageFactory.getWorkBookDataMethods().setStatusInSearch(status);
    }
}
