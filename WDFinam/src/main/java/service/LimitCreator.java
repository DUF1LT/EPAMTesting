package service;

import model.Limit;
import model.Stock;
import model.User;

public class LimitCreator
{
    public static final String COMPANY_NAME = "testdata.stock.companyname";
    public static final String STOCK_AMOUNT = "testdata.stock.amount";
    public static final String LIMIT_PRICE = "testdata.limit.price";

    public static Limit withCredentialsFromProperty()
    {
        return new Limit(new Stock(TestDataReader.getTestData(COMPANY_NAME), Integer.parseInt(TestDataReader.getTestData(STOCK_AMOUNT))),
                TestDataReader.getTestData(LIMIT_PRICE));
    }
}
