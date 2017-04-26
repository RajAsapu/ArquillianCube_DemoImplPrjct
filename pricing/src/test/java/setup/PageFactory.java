package setup;

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
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class PageFactory {

    private EventFiringWebDriver edriver;
    public enum page {CreateCalculation,ListCalculation,ListWorkBook,CreateWorkBook,ListIndex,CreateIndex,ListCurrencyExchange,
        CreateCurrencyExchange,ListFormula,CreateFormula};

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

    public ListCalculationRuleMethods getListCalculationRuleMethods() {
        if(listCalculationRuleMethods==null)
        {
            listCalculationRuleMethods = new ListCalculationRuleMethods();
        }
        return listCalculationRuleMethods;
    }


    public CreateCalculationRuleMethods getCreateCalculationRuleMethods() {
        if(createCalculationRuleMethods==null)
        {
            createCalculationRuleMethods = new CreateCalculationRuleMethods();
        }
        return createCalculationRuleMethods;
    }


    public ListWorkBookMethods getListWorkBookMethods() {
        if(listWorkBookMethods==null)
        {
            listWorkBookMethods = new ListWorkBookMethods();
        }
        return listWorkBookMethods;
    }

    public CreateWorkBookMethods getCreateWorkBookMethods() {
        if(createWorkBookMethods==null)
        {
            createWorkBookMethods = new CreateWorkBookMethods();
        }
        return createWorkBookMethods;
    }


    public ListIndexMethods getListIndexMethods() {
        if(listIndexMethods==null)
        {
            listIndexMethods = new ListIndexMethods();
        }
        return listIndexMethods;
    }

    public CreateIndexMethods getCreateIndexMethods() {
        if(createIndexMethods==null)
        {
            createIndexMethods = new CreateIndexMethods();
        }
        return createIndexMethods;
    }

    public ListCurrencyExchangeMethods getListCurrencyExchangeMethods() {
        if(listCurrencyExchangeMethods==null)
        {
            listCurrencyExchangeMethods = new ListCurrencyExchangeMethods();
        }
        return listCurrencyExchangeMethods;
    }

    public CreateCurrencyExchangeMethods getCreateCurrencyExchangeMethods() {
        if(createCurrencyExchangeMethods==null)
        {
            createCurrencyExchangeMethods = new CreateCurrencyExchangeMethods();
        }
        return createCurrencyExchangeMethods;
    }

    public ListFormulaMethods getListFormulaMethods() {
        if(listFormulaMethods==null)
        {
            listFormulaMethods = new ListFormulaMethods();
        }
        return listFormulaMethods;
    }

    public CreateFormulaMethods getCreateFormulaMethods() {
        if(createFormulaMethods==null)
        {
            createFormulaMethods = new CreateFormulaMethods();
        }
        return createFormulaMethods;
    }

    public IPage getPage(){
        IPage IPage = null;
        String currentPage = edriver.getCurrentUrl();

        if(currentPage.contains("calc-rule/list"))
        {
            IPage = new ListCalculationRuleMethods();
        }
        else if(currentPage.contains("calc-rule"))
        {
            IPage = new CreateCalculationRuleMethods();
        }
        else if(currentPage.contains("workbook/list"))
        {
            IPage = new ListWorkBookMethods();
        }
        else if(currentPage.contains("workbook"))
        {
            IPage = new CreateWorkBookMethods();
        }
        else if(currentPage.contains("index/list"))
        {
            IPage = new ListIndexMethods();
        }
        else if(currentPage.contains("index"))
        {
            IPage = new CreateIndexMethods();
        }
        else if(currentPage.contains("currency-exchange/list"))
        {
            IPage = new ListCurrencyExchangeMethods();
        }
        else if(currentPage.contains("currency-exchange"))
        {
            IPage = new CreateCurrencyExchangeMethods();
        }
        else if(currentPage.contains("formula/list"))
        {
            IPage = new ListFormulaMethods();
        }
        else if(currentPage.contains("formula"))
        {
            IPage = new CreateFormulaMethods();
        }

        return IPage;
    }

    public IPage getPage(page requestedPage)
    {
        IPage IPage = null;

        switch (requestedPage)
        {
            case ListCalculation:
                IPage = new ListCalculationRuleMethods();
                break;
            case CreateCalculation:
                IPage = new CreateCalculationRuleMethods();
                break;
            case ListWorkBook:
                IPage = new ListWorkBookMethods();
                break;
            case CreateWorkBook:
                IPage = new CreateWorkBookMethods();
                break;
            case ListIndex:
                IPage = new ListIndexMethods();
                break;
            case CreateIndex:
                IPage = new CreateIndexMethods();
                break;
            case ListCurrencyExchange:
                IPage = new ListCurrencyExchangeMethods();
                break;
            case CreateCurrencyExchange:
                IPage = new CreateCurrencyExchangeMethods();
                break;
            case ListFormula:
                IPage = new ListFormulaMethods();
                break;
            case CreateFormula:
                IPage = new CreateFormulaMethods();
                break;
        }
        return IPage;
    }


}
