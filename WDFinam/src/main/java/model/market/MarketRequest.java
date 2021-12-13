package model.market;

import model.BaseRequest;

import java.util.Objects;

public class MarketRequest extends BaseRequest{
    public MarketRequest(String company, String amount) {
        super(company, amount);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "company='" + company + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketRequest marketRequest = (MarketRequest) o;
        return amount == marketRequest.amount && Objects.equals(company, marketRequest.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, amount);
    }
}
