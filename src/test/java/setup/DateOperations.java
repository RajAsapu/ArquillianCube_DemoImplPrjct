package setup;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOperations {

    private EventFiringWebDriver edriver;
    private PageFactory pageFactory = null;

    public DateOperations() {
        pageFactory = new PageFactory();
        edriver = DriverBean.getDriver();
    }

    /*
     * Method to set the date
     * operation : today,tomorrow,displayed , today -10 , today +1
     * dataPicker : datePicker identifier in xpath
     * dateField : To clear the text , identifier in xpath
     */
    public void setDate(String operation, String datePicker, String dateField) {
        int days = 0;
        String date = operation, displayedDate;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD-MMM-YYYY");
        WebElement datepicker = edriver.findElement(By.xpath(datePicker));
        Actions act = new Actions(edriver);
        if (dateField != null) {
            edriver.findElement(By.xpath(dateField)).clear();
        }
        if (operation.contains("today")) {
            date = today.toString();
        } else if (operation.contains("tomorrow")) {
            date = today.plusDays(1).toString();
        } else if (operation.contains("yesterday")) {
            date = today.minusDays(1).toString();
        } else if (operation.contains("displayed")) {
            date = getDate(dateField);
        }

        if (!date.equals(operation)) {
            today = LocalDate.parse(date, formatter);
        }

        if (operation.contains("-")) {
            days = Integer.parseInt(operation.split("[+-]")[1]);
            date = today.minusDays(days).toString();
        } else if (operation.contains("+")) {
            days = Integer.parseInt(operation.split("[+-]")[1]);
            date = today.plusDays(days).toString();
        }

        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();
    }

    public String getDate(String identifier) {
        String value = null;
        value = edriver.findElement(By.xpath(identifier)).getAttribute("ng-reflect-value");
        return value == null ? edriver.findElement(By.xpath(identifier)).getText() : value;
    }

    public void getScreenShot() {
        File screenshot = ((TakesScreenshot) DriverBean.getDriver()).getScreenshotAs(OutputType.FILE);
        Scenario name;
        try {
            String timestamp = new Timestamp(System.currentTimeMillis()).toString();
            FileUtils.copyFile(screenshot, new File("screenshot/testRun" + timestamp.replaceAll(" ", "") + ".jpeg"));
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
