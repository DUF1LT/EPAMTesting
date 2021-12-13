package model.conditional;

import model.BaseRequest;

import javax.management.ObjectName;
import java.util.Objects;

public class ConditionalRequest extends BaseRequest {

    private Condition condition;
    private String conditionArgument;

    public ConditionalRequest(String company, String amount, Condition condition, String conditionArgument) {
        super(company, amount);
        this.condition = condition;
        this.conditionArgument = conditionArgument;
    }

    public ConditionalRequest(String company, String amount, String conditionArgument) {
        super(company, amount);
        this.conditionArgument = conditionArgument;
    }

    public Condition getCondition() { return condition; }
    public void setCondition(Condition condition) { this.condition = condition; }

    public String getConditionArgument() { return conditionArgument; }
    public void setConditionArgument(String conditionArgument) { this.conditionArgument = conditionArgument; }

    @Override
    public String toString() {
        return "ConditionalDeal{" +
                "company=" + company +
                ", condition=" + condition +
                ", conditionArgument='" + conditionArgument + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionalRequest that = (ConditionalRequest) o;
        return Objects.equals(company, that.company) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(conditionArgument, that.conditionArgument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, conditionArgument);
    }
}
