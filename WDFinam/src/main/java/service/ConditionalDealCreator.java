package service;

import model.Condition;
import model.ConditionalDeal;
import model.Stock;

public class ConditionalDealCreator {
    public static final String DEAL_CONDITION = "testdata.conditionaldeal.condition";
    public static final String CONDITION_ARGUMENT = "testdata.conditionaldeal.conditionargument";

    public static ConditionalDeal withCredentialsFromProperty()
    {
        return new ConditionalDeal(Condition.valueOf(Condition.class, TestDataReader.getTestData(DEAL_CONDITION)),
                TestDataReader.getTestData(CONDITION_ARGUMENT));
    }

    public static ConditionalDeal withEmptyCompanyName(){ return new ConditionalDeal(Condition.CompletionTime,
            TestDataReader.getTestData(CONDITION_ARGUMENT)); }
    public static ConditionalDeal withEmptyAmount(){ return new ConditionalDeal(Condition.valueOf(Condition.class, TestDataReader.getTestData(DEAL_CONDITION)),
            "");}

}
