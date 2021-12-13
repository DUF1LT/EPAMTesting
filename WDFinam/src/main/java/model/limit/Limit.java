package model.limit;

import model.BaseRequest;

import java.util.Objects;

public class Limit extends BaseRequest {
    private String price;

    public Limit(String company, String amount, String price) {
        super(company, amount);
        this.price = price;
    }

    public Limit(String company, String price) {
        super(company, "0");
        this.price = price;
    }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    @Override
    public String toString() {
        return "Limit{" +
                "price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Limit limit = (Limit) o;
        int minPriceLen = Math.min(price.length(), limit.price.length());
        return Objects.equals(company, limit.company) &&
                Objects.equals(amount, limit.amount) &&
                Objects.equals(price.substring(0, minPriceLen), limit.price.substring(0, minPriceLen));
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
