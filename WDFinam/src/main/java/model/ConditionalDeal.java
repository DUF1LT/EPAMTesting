package model;

import java.util.Objects;

public class ConditionalDeal {

    private DealCondition condition;
    private String conditionArgument;

    public ConditionalDeal(DealCondition condition, String conditionArgument)
    {
        this.condition = condition;
        this.conditionArgument = conditionArgument;
    }

    public DealCondition getCondition() { return condition; }
    public void setCondition(DealCondition condition) { this.condition = condition; }

    public String getConditionArgument() { return conditionArgument; }
    public void setConditionArgument(String conditionArgument) { this.conditionArgument = conditionArgument; }

    @Override
    public String toString() {
        return "ConditionalDeal{" +
                "condition=" + condition +
                ", conditionArgument='" + conditionArgument + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionalDeal that = (ConditionalDeal) o;
        return condition == that.condition && Objects.equals(conditionArgument, that.conditionArgument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, conditionArgument);
    }
}
