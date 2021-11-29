package service;

import model.Alert;
import model.AlertArgumentOption;
import model.AlertCondition;
import model.Stock;

public class AlertCreator {
    public static final String ALERT_CONDITION = "testdata.alert.condition";
    public static final String ALERT_ARGUMENT = "testdata.alert.conditionargument";
    public static final String ALERT_ARGUMENT_OPTION = "testdata.alert.conditionargumentoption";

    public static Alert withCredentialsFromProperty()
    {
        return new Alert(AlertCondition.valueOf(AlertCondition.class, TestDataReader.getTestData(ALERT_CONDITION)),
                TestDataReader.getTestData(ALERT_ARGUMENT),
                AlertArgumentOption.valueOf(AlertArgumentOption.class, TestDataReader.getTestData(ALERT_ARGUMENT_OPTION)));
    }
}
