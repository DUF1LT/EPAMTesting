package service.creator;

import model.conditional.Condition;
import model.conditional.ConditionalRequest;
import service.BaseRequestCreator;

public class ConditionalDealCreator extends BaseRequestCreator {
    public static final String DEAL_CONDITION = "testdata.conditionaldeal.condition";
    public static final String CONDITION_ARGUMENT = "testdata.conditionaldeal.conditionargument";

    public static ConditionalRequest withCredentialsFromProperty() {
        return new ConditionalRequest(TestDataReader.getTestData(COMPANY_NAME),
                TestDataReader.getTestData(STOCK_AMOUNT),
                Condition.valueOf(Condition.class, TestDataReader.getTestData(DEAL_CONDITION)),
                TestDataReader.getTestData(CONDITION_ARGUMENT));
    }
}
