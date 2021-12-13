package service.creator;

import model.limit.Limit;
import service.BaseRequestCreator;

public class LimitCreator extends BaseRequestCreator {
    public static final String LIMIT_PRICE = "testdata.limit.price";

    public static Limit withCredentialsFromProperty() {
        return new Limit(TestDataReader.getTestData(COMPANY_NAME),
                TestDataReader.getTestData(STOCK_AMOUNT),
                TestDataReader.getTestData(LIMIT_PRICE));
    }
}
