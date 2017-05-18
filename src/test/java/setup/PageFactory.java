package setup;

import com.gargoylesoftware.htmlunit.Page;
import functions.PageCommonMethods;
import functions.calculationrule.CreateCalculationRuleMethods;
import functions.calculationrule.ListCalculationRuleMethods;
import functions.currencyexchange.CreateCurrencyExchangeMethods;
import functions.currencyexchange.ListCurrencyExchangeMethods;
import functions.formula.CreateFormulaMethods;
import functions.formula.ListFormulaMethods;
import functions.index.CreateIndexMethods;
import functions.index.ListIndexMethods;
import functions.workbook.CreateWorkBookMethods;
import functions.workbook.ListWorkBookMethods;
import functions.workbook.WorkBookDataMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.awt.*;

public class PageFactory {

    private ListCalculationRuleMethods listCalculationRuleMethods = null;
    private CreateCalculationRuleMethods createCalculationRuleMethods = null;
    private ListWorkBookMethods listWorkBookMethods = null;
    private CreateWorkBookMethods createWorkBookMethods = null;
    private ListIndexMethods listIndexMethods = null;
    private CreateIndexMethods createIndexMethods = null;
    private ListCurrencyExchangeMethods listCurrencyExchangeMethods = null;
    private CreateCurrencyExchangeMethods createCurrencyExchangeMethods = null;
    private ListFormulaMethods listFormulaMethods = null;
    private CreateFormulaMethods createFormulaMethods = null;
    private WorkBookDataMethods workBookDataMethods = null;
    private PageCommonMethods pageCommonMethods = null;

    public PageCommonMethods getPageCommonMethods() {
            pageCommonMethods = new PageCommonMethods();
        return pageCommonMethods;
    }

    public WorkBookDataMethods getWorkBookDataMethods() {
            workBookDataMethods = new WorkBookDataMethods();
        return workBookDataMethods;
    }

    public ListCalculationRuleMethods getListCalculationRuleMethods() {
            listCalculationRuleMethods = new ListCalculationRuleMethods();
        return listCalculationRuleMethods;
    }


    public CreateCalculationRuleMethods getCreateCalculationRuleMethods() {
            createCalculationRuleMethods = new CreateCalculationRuleMethods();
        return createCalculationRuleMethods;
    }


    public ListWorkBookMethods getListWorkBookMethods() {
            listWorkBookMethods = new ListWorkBookMethods();
        return listWorkBookMethods;
    }

    public CreateWorkBookMethods getCreateWorkBookMethods() {
            createWorkBookMethods = new CreateWorkBookMethods();
        return createWorkBookMethods;
    }


    public ListIndexMethods getListIndexMethods() {
            listIndexMethods = new ListIndexMethods();
        return listIndexMethods;
    }

    public CreateIndexMethods getCreateIndexMethods() {
            createIndexMethods = new CreateIndexMethods();
        return createIndexMethods;
    }

    public ListCurrencyExchangeMethods getListCurrencyExchangeMethods() {
            listCurrencyExchangeMethods = new ListCurrencyExchangeMethods();
        return listCurrencyExchangeMethods;
    }

    public CreateCurrencyExchangeMethods getCreateCurrencyExchangeMethods() {
            createCurrencyExchangeMethods = new CreateCurrencyExchangeMethods();
        return createCurrencyExchangeMethods;
    }

    public ListFormulaMethods getListFormulaMethods() {
            listFormulaMethods = new ListFormulaMethods();
        return listFormulaMethods;
    }

    public CreateFormulaMethods getCreateFormulaMethods() {
            createFormulaMethods = new CreateFormulaMethods();
        return createFormulaMethods;
    }
}
