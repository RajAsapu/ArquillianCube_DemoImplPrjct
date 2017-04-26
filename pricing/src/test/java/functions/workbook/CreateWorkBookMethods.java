package functions.workbook;


import functions.GenericWebElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import javax.swing.*;
import java.util.List;

public class CreateWorkBookMethods extends GenericWebElementMethods{
    private EventFiringWebDriver edriver;

    public CreateWorkBookMethods(){ edriver = getEdriver();
    }

    public void setName(String name)
    {
        sendKeysToWE(Constants.workbookCreate_name_xpath,name);
    }
    public void setDescription(String description)
    {
        sendKeysToWE(Constants.workbookCreate_description_xpath,description);
    }
    public void setFormulaType(String formulaType)
    {
        selectFromDropDown(Constants.workbookCreate_formulaType_xpath,formulaType);
    }
    public void setSegmentType(String segmentType)
    {
        selectFromDropDown(Constants.workbookCreate_segmentType_xpath,segmentType);
    }
    public void hasDefaultValue(boolean hasDefaultValue)
    {
        if(hasDefaultValue)
        {
            clickButton(Constants.workbookCreate_defaultValueNo_xpath);
        }else{
            clickButton(Constants.workbookCreate_defaultValueYes_xpath);
        }
    }
    public void setDefaultValue(String defaultValue)
    {
        sendKeysToWE(Constants.workbookCreate_defaultValue_xpath,defaultValue);
    }
    public void addSingleAttribte()
    {
        clickButton(Constants.workbookCreate_addSingleAttribute);
    }
    public void addMultipleAttribte()
    {
        clickButton(Constants.workbookCreate_addMultipleAttributes);
    }
    public void removeSingleAttribte()
    {
        clickButton(Constants.workbookCreate_removeSingleAttributes);
    }
    public void removeMultipleAttribte()
    {
        clickButton(Constants.workbookCreate_removeMultipleAttributes);
    }
    public void selectAttribute(String attribute)
    {
        List<WebElement> attList = edriver.findElements(By.xpath(Constants.workbookCreate_attribute_xpath));

        for(WebElement temp:attList)
        {
            if(temp.getText().equals(attribute))
            {
                if(!temp.isDisplayed())
                {
                    scrollIntoView(Constants.workbookCreate_attributeListView_xpath);
                }
                temp.click();
                break;
            }
        }
        addSingleAttribte();
    }
    public void selectMultipleAttributes(String[] attributes)
    {
        Actions act = new Actions(edriver);
        List<WebElement> attList = edriver.findElements(By.xpath(Constants.workbookCreate_attribute_xpath));

        for(int i=0;i<attributes.length;i++)
        {
            if(attList.get(i).getText().equals(attributes[i]))
            {
                if(!attList.get(i).isDisplayed())
                {
                    scrollIntoView(Constants.workbookCreate_attributeListView_xpath);
                }
                act.keyDown(Keys.CONTROL).click(attList.get(i)).keyUp(Keys.CONTROL).perform();
                break;
            }
        }
        addMultipleAttribte();
    }

}
