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
import java.util.Map;

public class PageWorkbooksteps extends PageCommonMethods {

    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String FORMULATYPE = "formulaType";
    private static final String SEGMENT = "segment";
    private static final String ROW = "row";
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
    String temp;
    private PageFactory pageFactory;


    public PageWorkbooksteps() {
        pageFactory = new PageFactory();
    }

    @When("^name is set to \"([^\"]*)\"$")
    public void name_is_set_to(String name) {
        pageFactory.getCreateWorkBookMethods().setName(name, false);
    }

    @When("^the user creates workbook with name as \"([^\"]*)\"$")
    public void name_creates_workbook_with_name_as(String name) {
        pageFactory.getCreateWorkBookMethods().setName(name, false);
    }

    @And("^description is set to \"([^\"]*)\"$")
    public void description_is_set_to(String description) {
        pageFactory.getCreateWorkBookMethods().setDescription(description);
    }

    @And("^formula type is set to \"([^\"]*)\"$")
    public void formula_type_is_set_to(String formulaType) {
        pageFactory.getCreateWorkBookMethods().setFormulaType(formulaType);
    }

    @And("^segment type is set to \"([^\"]*)\"$")
    public void segment_type_is_set_to(String segmentType) {
        pageFactory.getCreateWorkBookMethods().setSegmentType(segmentType);
    }

    @And("^set the default value to \"([^\"]*)\"$")
    public void set_the_default_value_to(String defaultValueTo) {
        pageFactory.getCreateWorkBookMethods().setDefaultValue(defaultValueTo);
    }

    @And("^(doesn't have|has) a default value$")
    public void doesn_t_have_a_default_value(String cond) {
        if (cond.contains("doesn't")) {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(false);
        } else {
            pageFactory.getCreateWorkBookMethods().hasDefaultValue(true);
        }
    }

    @Then("^the application doesn't display edit and deactive actions$")
    public void the_application_doesnt_display_edit_and_deactivate_actions() {
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
    public void the_list_workbook_configuration_list_should_be_filtered_with(String filter, String filterValue) {
        if (!filterValue.equals("firstWorkbook")) {
            temp = filterValue;
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
            case VIEW_WORKBOOK_CONFIGURATION:
                pageFactory.getListWorkBookMethods().clickOnViewWorkBookConfiguration(0);
                break;
            case MANAGE_DATA:
                pageFactory.getListWorkBookMethods().clickOnManageData(0);
                break;
            default:
                Assert.fail("Option not found in the list");
        }
    }

    @And("^the user adds new workbook data$")
    public void the_user_adds_new_data() {
        pageFactory.getWorkBookDataMethods().clickOnAddNewData();
    }

    @And("^the user edits workbook data$")
    public void the_user_edits_new_data() {
        pageFactory.getWorkBookDataMethods().clickOnDataWithEditEnabled(STATUS_ACTIVE);
    }

    @And("^the user deactivates workbook data$")
    public void the_user_deactivates_workbook_data() {
        pageFactory.getWorkBookDataMethods().clickOnDataWithDeActivateEnabled(STATUS_ACTIVE);
        pageFactory.getWorkBookDataMethods().deactivateRecord();
    }

    @And("^the user selects inactive workbook data$")
    public void the_user_selects_inactive_workbook_data() {
        pageFactory.getWorkBookDataMethods().clickOnDataWithInactiveStatus(STATUS_INACTIVE);
    }

    @And("^the user sets status for the workbook data as ([^\"]*)$")
    public void sets_the_status_as(String status) {
        pageFactory.getListCurrencyExchangeMethods().setStatus(status);
    }

    @And("^the user navigates to the search page of workbook data$")
    public void the_searches_for_workbook_data() {
        pageFactory.getWorkBookDataMethods().clickOnSearch();
    }

    @And("^the definition should be displayed with the below details$")
    public void the_definition_should_be_displayed_with_below_details(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        pageFactory.getListWorkBookMethods().checkIfRecordExistsUsingNameFilter(rows.get(0).get("name"));
        pageFactory.getListWorkBookMethods().selectWorkbookConfig(rows.get(0).get("name"));
        pageFactory.getListWorkBookMethods().clickOnViewWorkBookConfiguration(0);
        pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationIsDisplayed(dataTable);
    }

    @And("^the user chooses a ([^\"]*) to upload$")
    public void the_user_chooses_a_file_to_upload(String filePath) {
        pageFactory.getWorkBookDataMethods().clickOnUpload();
        pageFactory.getWorkBookDataMethods().clickOnChoose(filePath);
    }

    @When("^the user views workbook configuration of ([^\"]*)$")
    public void the_user_views_workbook_configuration_of(String workBookName) {
        pageFactory.getListWorkBookMethods().viewDefinitionWithName(workBookName);
    }

    @When("^the user navigates to the workbook data of ([^\"]*)$")
    public void the_user_navigates_to_the_workbook_data_of(String workBookName) {
        pageFactory.getListWorkBookMethods().viewDataForDefinitionWithName(workBookName);
    }

    @Then("^the user is only allowed to read the attributes in workbook (configuration|data)$")
    public void the_user_is_only_allowed_to_read_the_attributes_in_workbook_configuration(String key) {
        if (key.equals("configuration")) {
            pageFactory.getCreateWorkBookMethods().verifyIfWorkbookConfigurationDefinitionIsReadOnly();
        } else {
            pageFactory.getWorkBookDataMethods().verifyIfWorkbookDataIsReadOnly();
        }
    }

    @And("^none of the attributes are selected$")
    public void none_of_the_attributes_are_selected() {
        pageFactory.getCreateWorkBookMethods().removeMultipleAttribte();
    }
}