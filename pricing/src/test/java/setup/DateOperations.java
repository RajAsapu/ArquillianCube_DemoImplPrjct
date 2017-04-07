package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOperations {
    private EventFiringWebDriver edriver;

    public DateOperations()
    {
        edriver = DriverBean.getDriver();
    }

    public void setDate(String operation,String datePicker,String dateField)throws Exception
    {
        int days=0;
        String date = operation,displayedDate;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        WebElement datepicker = edriver.findElement(By.xpath(datePicker));
        Actions act = new Actions(edriver);

        edriver.findElement(By.xpath(dateField)).clear();
        if(operation.contains("today")) {
            date = today.toString();
        }else if(operation.contains("tomorrow")){
            date = today.plusDays(1).toString();
        }else if(operation.contains("yesterday")){
            date = today.minusDays(1).toString();
        }else if(operation.contains("displayed")){
            date = getDate(dateField);
        }

        if(!date.equals(operation)){
            today = LocalDate.parse(date, formatter);
        }

        if(operation.contains("-")) {
            days = Integer.parseInt(operation.split("[+-]")[1]);
            date = today.minusDays(days).toString();
        }else if(operation.contains("+")){
            days = Integer.parseInt(operation.split("[+-]")[1]);
            date = today.plusDays(days).toString();
        }

        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();
    }
    public String getDate(String identifier)
    {
        String value = null;
        value = edriver.findElement(By.xpath(identifier)).getAttribute("ng-reflect-value");
        return value == null ? edriver.findElement(By.xpath(identifier)).getText() : value;
    }
}
