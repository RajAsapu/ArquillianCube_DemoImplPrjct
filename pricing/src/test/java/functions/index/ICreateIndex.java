package functions.index;

import setup.IPage;

public interface ICreateIndex extends IPage {
    public void setName(String name);
    public void setStartDate(String startDate);
    public void setEndDate(String endDate);
    public void setCurrency(String currency);
    public void setUom(String uom);
    public void setRateBasis(String rateBasis);
    public void setLowPrice(String lowPrice);
    public void setMidPrice(String midPrice);
    public void setHighPrice(String highPrice);
    public void setClosePrice(String closePrice);
    public void setComments(String comment);
}
