package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.table.TableConverter;
import functions.*;
import gherkin.formatter.model.DataTableRow;
import org.apache.log4j.Logger;
import setup.Constants;
import setup.PageFactory;

import java.util.List;

public class PageWorkbooksteps extends PageCommonMethods {

    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    private PageFactory pageFactory;
    String temp;


    public PageWorkbooksteps()
    {
        pageFactory = new PageFactory();
    }

    @When("^name is set to \"([^\"]*)\"$")
    public void name_is_set_to(String name) throws Throwable {
        pageFactory.getCreateWorkBookMethods().setName(name);
    }

    @And("^description is set to \"([^\"]*)\"$")
    public void description_is_set_to(String description) throws Throwable {
        pageFactory.getCreateWorkBookMethods().setDescription(description);
    }

    @And("^formula type is set to \"([^\"]*)\"$")
    public void formula_type_is_set_to(String formulaType) throws Throwable {
        pageFactory.getCreateWorkBookMethods().setFormulaType(formulaType);
    }

    @And("^segment type is set to \"([^\"]*)\"$")
    public void segment_type_is_set_to(String segmentType) throws Throwable {
        pageFactory.getCreateWorkBookMethods().setSegmentType(segmentType);
      }

    @And("^set the default value to \"([^\"]*)\"$")
    public void set_the_default_value_to(String defaultValueTo) throws Throwable {
        pageFactory.getCreateWorkBookMethods().setDefaultValue(defaultValueTo);
    }

    @And("^(doesn't have|has) a default value$")
    public void doesn_t_have_a_default_value(String cond) throws Throwable {
        if(cond.contains("doesn't"))
        {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(false);
        }else {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(true);
        }
    }

    @And("^select below attributes$")
    public void select_below_attributes(DataTable dataTable)
    {
        List<List<String>> table = dataTable.raw();

        for(int i=0;i<table.size();i++)
        {
            pageFactory.getCreateWorkBookMethods().selectAttribute(table.get(i).get(0));
        }
    }

    @And("^select all the attributes$")
    public void select_all_the_attributes()
    {
        pageFactory.getCreateWorkBookMethods().addMultipleAttribte();
    }

    @Then("^the workbook configuration (should|should not) be created$")
    public void the_workbook_configuration_should_be_created(String cond)throws Exception
    {
        if(cond.contains("not"))
        {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsCreated(false);
        }
        else
        {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsCreated(true);
        }
    }

    @And("^click on add new workbook configuration$")
    public void click_on_add_new_workbook_configuration()
    {
        pageFactory.getListWorkBookMethods().clickAddNewWorkBookConfiguration();
    }

    @When("^the user filters workbook configuration list using \"([^\"]*)\"$")
    public void the_user_filters_workbook_configuration_list_using(String filter)throws Exception
    {
        Thread.sleep(3000);
        temp = pageFactory.getListWorkBookMethods().getFirstRecord(filter);
    }

    @Then("^the list workbook configuration list should be filtered with the \"([^\"]*)\"$")
    public void the_list_workbook_configuration_list_should_be_filtered_with(String filter)
    {
        switch (filter)
        {
            case "name" : pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingNameFilter(temp);
                break;
            case "description" : pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingDescriptionFilter(temp);
                break;
            case "formulaType" : pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingFormulaTypeFilter(temp);
                break;
            case "segment" : pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingSegmentFilter(temp);
                break;
        }
    }

    @And("^clicked on \"([^\"]*)\"")
    public void clicked_on_(String button)throws Exception
    {
        switch (button.toLowerCase())
        {
            case "data" : pageFactory.getListWorkBookMethods().clickOnData(0);
                break;
            case "definition" : pageFactory.getListWorkBookMethods().clickOnDefinition(0);
                break;
            case "add new data" : pageFactory.getWorkBookDataMethods().clickOnAddNewData();
                break;
            case "update" : pageFactory.getWorkBookDataMethods().clickOnUpdate();
        }
    }

    @And("^the definition should be displayed with the below details$")
    public void the_definition_should_be_displayed_with_below_details(DataTable dataTable)
    {
        List<List<String>> rows = dataTable.transpose().raw();
        pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingNameFilter(rows.get(0).get(0));
        pageFactory.getListWorkBookMethods().clickOnDefinition(0);
        pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsDisplayed(dataTable);
    }
}
