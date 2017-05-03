package stepdef;

import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import setup.PageFactory;

public class PageWorkbookDataSteps {
    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    private PageFactory pageFactory;
    String temp;

    public PageWorkbookDataSteps()
    {
        pageFactory = new PageFactory();
    }

    @And("^set the start date for data as \"([^\"]*)\"$")
    public void set_the_start_date_for_data_as(String startDate)
    {
        pageFactory.getWorkBookDataMethods().setStartDate(startDate);
    }
    @And("^set the end date for data as \"([^\"]*)\"$")
    public void set_the_end_date_for_data_as(String endDate)
    {
        pageFactory.getWorkBookDataMethods().setEndDate(endDate);
    }


}
