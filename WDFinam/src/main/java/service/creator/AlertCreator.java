package service.creator;

import model.alert.Alert;
import model.alert.AlertArgumentOption;
import model.alert.Condition;
import service.BaseRequestCreator;

public class AlertCreator extends BaseRequestCreator {
    public static final String ALERT_CONDITION = "testdata.alert.condition";
    public static final String ALERT_ARGUMENT = "testdata.alert.conditionargument";
    public static final String ALERT_ARGUMENT_OPTION = "testdata.alert.conditionargumentoption";

    public static Alert withCredentialsFromProperty() {
        return new Alert (TestDataReader.getTestData(COMPANY_NAME) , Condition.valueOf(Condition.class, TestDataReader.getTestData(ALERT_CONDITION)),
                TestDataReader.getTestData(ALERT_ARGUMENT),
                AlertArgumentOption.valueOf(AlertArgumentOption.class, TestDataReader.getTestData(ALERT_ARGUMENT_OPTION)));
    }
}
