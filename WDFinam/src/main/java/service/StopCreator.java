package service;

import model.Stock;
import model.Stop;

public class StopCreator {
    public static final String STOP_LOSS_ENABLE = "testdata.stop.stoplossenabled";
    public static final String STOP_LOSS_PRICE = "testdata.stop.stoplossprice";

    public static final String TAKE_PROFIT_ENABLE = "testdata.stop.takeprofitenabled";
    public static final String TAKE_PROFIT_PRICE = "testdata.stop.takeprofitprice";

    public static Stop withCredentialsFromProperty()
    {
        return new Stop(Boolean.parseBoolean(TestDataReader.getTestData(STOP_LOSS_ENABLE)),
                TestDataReader.getTestData(STOP_LOSS_PRICE),
                Boolean.parseBoolean(TestDataReader.getTestData(TAKE_PROFIT_ENABLE)),
                TestDataReader.getTestData(TAKE_PROFIT_PRICE));
    }
}
