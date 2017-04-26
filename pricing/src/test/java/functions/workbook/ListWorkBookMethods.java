package functions.workbook;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ListWorkBookMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    public ListWorkBookMethods()
    {
        edriver = getEdriver();
    }
}
