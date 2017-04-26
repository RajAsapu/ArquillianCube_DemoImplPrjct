package functions.currencyexchange;

import setup.IPage;

public interface IListCurrencyExchange extends IPage{
    public void setSearchType(String searchType);
    public void setStartDate(String startDate);
    public void setEndDate(String endDate);
    public void setStatus(String status);
    public void setConverstionType(String converstionType);
    public void setCurrencyFrom(String currencyFrom);
    public void setCurrencyTo(String currencyTo);
    public void clickOnSearch();
    public void verifyIfThePageIsListPage();
    public void verifyIfStatusMatchingTheFilter(String status);
    public void verifyIfRateTypeMatchingTheFilter(String rateType);
    public void verifyIfRateMatchingTheFilter();
    public void verifyIfCurrenyFromIsMatchingTheFilter(String currencyFrom);
    public void verifyIfCurrenyToIsMatchingTheFilter(String currencyTo);
    public void verifyIfDateIsMatchingTheFilter(String fromDate);
}
