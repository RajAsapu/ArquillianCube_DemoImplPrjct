package functions.currencyexchange;

import setup.IPage;

public interface ICreateCurrencyExchange extends IPage{
    public void setDate(String date);
    public void setType(String type);
    public void setFromCurrency(String fromCurrency);
    public void setToCurrency(String toCurrency);
    public void setConverstionRate(String rate);
}
