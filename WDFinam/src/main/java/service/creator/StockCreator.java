package service.creator;

import model.market.MarketRequest;

public class StockCreator {
    public static final String COMPANY_NAME = "testdata.marketRequest.companyname";
    public static final String STOCK_AMOUNT = "testdata.marketRequest.amount";

    public static MarketRequest withCredentialsFromProperty() {
        return new MarketRequest(TestDataReader.getTestData(COMPANY_NAME), TestDataReader.getTestData(STOCK_AMOUNT));
    }
}
