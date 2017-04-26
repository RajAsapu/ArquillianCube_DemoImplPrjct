package stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import functions.*;
import org.apache.log4j.Logger;
import setup.PageFactory;

public class PageWorkbooksteps extends PageCommonMethods {

    final static Logger logger = Logger.getLogger(PageWorkbooksteps.class.getName());
    private PageFactory pageFactory;


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
}
