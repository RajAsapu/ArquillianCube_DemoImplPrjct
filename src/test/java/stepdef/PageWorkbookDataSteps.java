package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import setup.PageFactory;

import java.util.List;
import java.util.Map;

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
    public void the_workbook_data_should_be_created(String cond, String placeHolder, DataTable table) throws Exception {
        if (cond.contains("not")) {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(false, table);
        } else {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsCreated(true, table);
        }
    }

    @And("^(supplier|supplierSite|location|item|hauler|customerShipto|customer|fbo|price basis|uom|set the currency for data|set the amount for data) as \"([^\"]*)\"$")
    public void attribute_as_attribute(String attribute, String attributeValue) {
        if (!attributeValue.equals(null) && !attributeValue.isEmpty()) {
            pageFactory.getWorkBookDataMethods().setAttribute(attribute, attributeValue);
        }
    }

    @And("^create the workbook data$")
    public void create_the_work_data(DataTable table) {
        List<Map<String,String>> dataList = table.asMaps(String.class,String.class);
        /*
         * | supplier | location| customer| priceBasis | uom | startDate| endDate | currencyCode | amount |
         */
        for (Map<String,String> row:dataList) {
            pageFactory.getWorkBookDataMethods().clickOnAddNewData();
            pageFactory.getWorkBookDataMethods().setAttribute("supplier", row.get("supplier"));
            pageFactory.getWorkBookDataMethods().setAttribute("location", row.get("location"));
            pageFactory.getWorkBookDataMethods().setAttribute("customer", row.get("customer"));
            pageFactory.getWorkBookDataMethods().setAttribute("price basis", row.get("priceBasis"));
            pageFactory.getWorkBookDataMethods().setAttribute("uom", row.get("uom"));
            pageFactory.getWorkBookDataMethods().setStartDate(row.get("startDate"));
            pageFactory.getWorkBookDataMethods().setEndDate(row.get("endDate"));
            pageFactory.getWorkBookDataMethods().setAttribute("set the currency for data", row.get("currencyCode"));
            pageFactory.getWorkBookDataMethods().setAttribute("set the amount for data", row.get("amount"));
            pageFactory.getWorkBookDataMethods().clickOnSaveButton();
        }
    }

    @And("^edit the record with the supplier name as \"([^\"]*)\"")
    public void edit_the_record_with_the_supplier_name_as(String supplierName) {
        pageFactory.getWorkBookDataMethods().clickOnDataWithSupplier(supplierName);
        pageFactory.getWorkBookDataMethods().clickOnUpdate();
    }
}
