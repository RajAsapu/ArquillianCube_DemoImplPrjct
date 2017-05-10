package setup;

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
        if (pageCommonMethods == null) {
            pageCommonMethods = new PageCommonMethods();
        }
        return pageCommonMethods;
    }

    public WorkBookDataMethods getWorkBookDataMethods() {
        if (workBookDataMethods == null) {
            workBookDataMethods = new WorkBookDataMethods();
        }
        return workBookDataMethods;
    }

    public ListCalculationRuleMethods getListCalculationRuleMethods() {
        if (listCalculationRuleMethods == null) {
            listCalculationRuleMethods = new ListCalculationRuleMethods();
        }
        return listCalculationRuleMethods;
    }


    public CreateCalculationRuleMethods getCreateCalculationRuleMethods() {
        if (createCalculationRuleMethods == null) {
            createCalculationRuleMethods = new CreateCalculationRuleMethods();
        }
        return createCalculationRuleMethods;
    }


    public ListWorkBookMethods getListWorkBookMethods() {
        if (listWorkBookMethods == null) {
            listWorkBookMethods = new ListWorkBookMethods();
        }
        return listWorkBookMethods;
    }

    public CreateWorkBookMethods getCreateWorkBookMethods() {
        if (createWorkBookMethods == null) {
            createWorkBookMethods = new CreateWorkBookMethods();
        }
        return createWorkBookMethods;
    }


    public ListIndexMethods getListIndexMethods() {
        if (listIndexMethods == null) {
            listIndexMethods = new ListIndexMethods();
        }
        return listIndexMethods;
    }

    public CreateIndexMethods getCreateIndexMethods() {
        if (createIndexMethods == null) {
            createIndexMethods = new CreateIndexMethods();
        }
        return createIndexMethods;
    }

    public ListCurrencyExchangeMethods getListCurrencyExchangeMethods() {
        if (listCurrencyExchangeMethods == null) {
            listCurrencyExchangeMethods = new ListCurrencyExchangeMethods();
        }
        return listCurrencyExchangeMethods;
    }

    public CreateCurrencyExchangeMethods getCreateCurrencyExchangeMethods() {
        if (createCurrencyExchangeMethods == null) {
            createCurrencyExchangeMethods = new CreateCurrencyExchangeMethods();
        }
        return createCurrencyExchangeMethods;
    }

    public ListFormulaMethods getListFormulaMethods() {
        if (listFormulaMethods == null) {
            listFormulaMethods = new ListFormulaMethods();
        }
        return listFormulaMethods;
    }

    public CreateFormulaMethods getCreateFormulaMethods() {
        if (createFormulaMethods == null) {
            createFormulaMethods = new CreateFormulaMethods();
        }
        return createFormulaMethods;
    }
}