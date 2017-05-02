package stepdef;

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



}
