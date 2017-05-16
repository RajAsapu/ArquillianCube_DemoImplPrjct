package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functions.PageCommonMethods;
import org.apache.log4j.Logger;
import org.junit.Assert;
import setup.PageFactory;

import java.util.List;

public class PageWorkbooksteps extends PageCommonMethods {

    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    String temp;
    private PageFactory pageFactory;

    private static final String NAME ="name";
    private static final String DESCRIPTION ="description";
    private static final String FORMULATYPE ="formulaType";
    private static final String SEGMENT ="segment";
    private static final String ROW ="row";
    private static final String VIEW_WORKBOOK_CONFIGURATION = "view workbook configuration";
    private static final String ADD_NEW_DATA = "add new data";
    private static final String EDIT_DATA = "edit data";
    private static final String UPLOAD = "upload";
    private static final String SEARCH = "search";
    private static final String MANAGE_DATA = "manage data";
    private static final String DEACTIVATE = "deactivate";
    private static final String INACTIVE_DATA = "inactive data";
    private static final String STATUS_ACTIVE = "Active";
    private static final String STATUS_INACTIVE = "Inactive";


    public PageWorkbooksteps() {
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
        if (cond.contains("doesn't")) {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(false);
        } else {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(true);
        }
    }

    @Then("^the application doesn't display edit and deactive actions$")
    public void the_application_doesnt_display_edit_and_deactivate_actions()
    {
        pageFactory.getWorkBookDataMethods().verifyIfEditAndDeactivateIsDisplayed();
    }

    @And("^select below attributes$")
    public void select_below_attributes(DataTable dataTable) {
        List<List<String>> table = dataTable.raw();

        for (int i = 0; i < table.size(); i++) {
            pageFactory.getCreateWorkBookMethods().selectAttribute(table.get(i).get(0));
        }
    }

    @And("^select all the attributes$")
    public void select_all_the_attributes() {
        pageFactory.getCreateWorkBookMethods().addMultipleAttribte();
    }

    @Then("^the workbook configuration (should|should not) be created$")
    public void the_workbook_configuration_should_be_created(String cond) throws Exception {
        if (cond.contains("not")) {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsCreated(false);
        } else {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsCreated(true);
        }
    }

    @And("^click on add new workbook configuration$")
    public void click_on_add_new_workbook_configuration() {
        pageFactory.getListWorkBookMethods().clickAddNewWorkBookConfiguration();
    }

    @When("^the user filters workbook configuration list using \"([^\"]*)\"$")
    public void the_user_filters_workbook_configuration_list_using(String filter) throws Exception {
        Thread.sleep(3000);
        temp = pageFactory.getListWorkBookMethods().getFirstRecord(filter);
    }

    @Then("^the list workbook configuration list should be filtered with the \"([^\"]*)\" as \"([^\"]*)\"$")
    public void the_list_workbook_configuration_list_should_be_filtered_with(String filter,String filterValue) {
        if(!filterValue.equals("firstWorkbook"))
        {
            temp=filterValue;
        }
        switch (filter) {
            case NAME:
                pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingNameFilter(temp);
                break;
            case DESCRIPTION:
                pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingDescriptionFilter(temp);
                break;
            case FORMULATYPE:
                pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingFormulaTypeFilter(temp);
                break;
            case SEGMENT:
                pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingSegmentFilter(temp);
                break;
        }
    }

    @And("^clicked on \"([^\"]*)\"")
    public void clicked_on_(String button) throws Exception {
        switch (button.toLowerCase()) {
            case ROW:
                pageFactory.getListWorkBookMethods().clickOnRadioButton(0);
                break;
            case VIEW_WORKBOOK_CONFIGURATION:
                pageFactory.getListWorkBookMethods().clickOnViewWorkBookConfiguration(0);
                break;
            case ADD_NEW_DATA:
                pageFactory.getWorkBookDataMethods().clickOnAddNewData();
                break;
            case EDIT_DATA:
                pageFactory.getWorkBookDataMethods().clickOnDataWithEditEnabled(STATUS_ACTIVE);
                break;
            case UPLOAD:
                pageFactory.getWorkBookDataMethods().clickOnUpload();
                break;
            case SEARCH:
                pageFactory.getWorkBookDataMethods().clickOnSearch();
                break;
            case MANAGE_DATA:
                pageFactory.getListWorkBookMethods().clickOnManageData(0);
                break;
            case DEACTIVATE:
                pageFactory.getWorkBookDataMethods().clickOnDataWithDeActivateEnabled(STATUS_ACTIVE);
                pageFactory.getWorkBookDataMethods().deactivateRecord();
                break;
            case INACTIVE_DATA:
                pageFactory.getWorkBookDataMethods().clickOnDataWithInactiveStatus(STATUS_INACTIVE);
                break;
            default:
                Assert.fail("Option not found in the list");
        }
    }

    @And("^the definition should be displayed with the below details$")
    public void the_definition_should_be_displayed_with_below_details(DataTable dataTable) {
        List<List<String>> rows = dataTable.transpose().raw();
        pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingNameFilter(rows.get(0).get(0));
        pageFactory.getListWorkBookMethods().clickOnRadioButton(0);
        pageFactory.getListWorkBookMethods().clickOnViewWorkBookConfiguration(0);
        pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsDisplayed(dataTable);
    }

    @And("^choose file having path \"([^\"]*)\"$")
    public void choose_file_having_path(String filePath)
    {
        pageFactory.getWorkBookDataMethods().clickOnChoose(filePath);
    }

    @When("^the user clicks on (manage data|view workbook configuration) for a workbook with name as \"([^\"]*)\"$")
    public void the_user_clicks_on_manage_data_for_a_workbook_with_name_as(String fn,String workBookName)
    {
        if(fn.contains("workbook configuration"))
        {
            pageFactory.getListWorkBookMethods().viewDefinitionWithName(workBookName);
        }
        else
        {
            pageFactory.getListWorkBookMethods().viewDataForDefinitionWithName(workBookName);
        }
    }

    @Then("^the user is only allowed to read the attributes in workbook (configuration|data)$")
    public void the_user_is_only_allowed_to_read_the_attributes_in_workbook_configuration(String key)
    {
        if(key.equals("configuration")) {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationDefinitionIsReadOnly();
        }else{
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsReadOnly();
        }
    }
}
