package model;

import java.util.Objects;

public class Alert {
    private AlertCondition alertCondition;
    private String alertArgument;
    private AlertArgumentOption alertArgumentOption;

    public Alert(AlertCondition alertCondition, String alertArgument, AlertArgumentOption alertArgumentOption)
    {
        this.alertCondition = alertCondition;
        this.alertArgument = alertArgument;
        this.alertArgumentOption = alertArgumentOption;
    }

    public AlertCondition getAlertCondition() { return alertCondition; }
    public void setAlertCondition(AlertCondition alertCondition) { this.alertCondition = alertCondition; }

    public String getAlertArgument() { return alertArgument; }
    public void setAlertArgument(String alertArgument) { this.alertArgument = alertArgument; }

    public AlertArgumentOption getAlertArgumentOption() { return alertArgumentOption; }
    public void setAlertArgumentOption(AlertArgumentOption alertArgumentOption) { this.alertArgumentOption = alertArgumentOption; }

    @Override
    public String toString() {
        return "Alert{" +
                "alertCondition=" + alertCondition +
                ", alertArgument='" + alertArgument + '\'' +
                ", alertArgumentOption=" + alertArgumentOption +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return alertCondition == alert.alertCondition && Objects.equals(alertArgument, alert.alertArgument) && alertArgumentOption == alert.alertArgumentOption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alertCondition, alertArgument, alertArgumentOption);
    }
}
