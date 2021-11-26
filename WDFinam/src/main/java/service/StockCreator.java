package service;

import model.Stock;

public class StockCreator {
    public static final String COMPANY_NAME = "testdata.stock.companyname";
    public static final String STOCK_AMOUNT = "testdata.stock.amount";

    public static Stock withCredentialsFromProperty()
    {
        return new Stock(TestDataReader.getTestData(COMPANY_NAME), Integer.parseInt(TestDataReader.getTestData(STOCK_AMOUNT)));
    }

    public static Stock withEmptyCompanyName(){ return new Stock("",Integer.parseInt(TestDataReader.getTestData(STOCK_AMOUNT))); }
    public static Stock withEmptyAmount(){ return new Stock(TestDataReader.getTestData(COMPANY_NAME), 0);}

}
