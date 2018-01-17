package runner;

import cucumber.runtime.arquillian.ArquillianCucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(ArquillianCucumber.class)

public class RunTest {
    private static Logger logger = LoggerFactory.getLogger(RunTest.class);
    @Test
    public void verifyIfJenkinsRuns()
    {
        logger.info("Jenkins is running");
    }
    public void test() {}
}
