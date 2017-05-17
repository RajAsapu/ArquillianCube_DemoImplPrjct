package setup;

import cucumber.api.java.After;
import functions.PageCommonMethods;

public class Hooks {
    @After
    public void tearDown() {
        new PageCommonMethods().closeBrowser();
    }
}

