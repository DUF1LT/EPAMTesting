package model.alert;

import model.BaseRequest;
import java.util.Objects;

public class Alert{
    private String company;
    private Condition condition;
    private String alertArgument;
    private AlertArgumentOption alertArgumentOption;

    public Alert(String company, Condition condition, String alertArgument, AlertArgumentOption alertArgumentOption) {
        this.company = company;
        this.condition = condition;
        this.alertArgument = alertArgument;
        this.alertArgumentOption = alertArgumentOption;
    }

    public Alert(String company, Condition condition, String alertArgument) {
        this.company = company;
        this.condition = condition;
        this.alertArgument = alertArgument;
        this.alertArgumentOption = AlertArgumentOption.LastPrice;
    }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public Condition getAlertCondition() { return condition; }
    public void setAlertCondition(Condition condition) { this.condition = condition; }

    public String getAlertArgument() { return alertArgument; }
    public void setAlertArgument(String alertArgument) { this.alertArgument = alertArgument; }

    public AlertArgumentOption getAlertArgumentOption() { return alertArgumentOption; }
    public void setAlertArgumentOption(AlertArgumentOption alertArgumentOption) { this.alertArgumentOption = alertArgumentOption; }

    @Override
    public String toString() {
        return "Alert{" +
                "alertCondition=" + condition +
                ", alertArgument='" + alertArgument + '\'' +
                ", alertArgumentOption=" + alertArgumentOption +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        int minArgumentLen = Math.min(alertArgument.length(), alert.alertArgument.length());
        return condition == alert.condition && Objects.equals(alertArgument.substring(0, minArgumentLen - 1), alert.alertArgument.substring(0, minArgumentLen - 1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, alertArgument, alertArgumentOption);
    }
}
