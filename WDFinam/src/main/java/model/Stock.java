package model;

import java.util.Objects;

public class Stock {
    private String company;
    private int amount;

    public Stock(String company, int amount)
    {
        this.company = company;
        this.amount = amount;
    }

    public String getCompany(){ return company; }
    public void setCompany(String company){ this.company = company; }

    public int getAmount(){ return amount; }
    public void setAmount(int amount){ this.amount = amount; }

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
        Stock stock = (Stock) o;
        return amount == stock.amount && Objects.equals(company, stock.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, amount);
    }
}
