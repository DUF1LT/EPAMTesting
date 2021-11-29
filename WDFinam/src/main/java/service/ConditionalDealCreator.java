package service;

import model.DealCondition;
import model.ConditionalDeal;

public class ConditionalDealCreator {
    public static final String DEAL_CONDITION = "testdata.conditionaldeal.condition";
    public static final String CONDITION_ARGUMENT = "testdata.conditionaldeal.conditionargument";

    public static ConditionalDeal withCredentialsFromProperty()
    {
        return new ConditionalDeal(DealCondition.valueOf(DealCondition.class, TestDataReader.getTestData(DEAL_CONDITION)),
                TestDataReader.getTestData(CONDITION_ARGUMENT));
    }
}
