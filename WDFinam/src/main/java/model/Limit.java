package model;

public class Limit {
    private Stock stock;
    private String price;

    public Limit(Stock stock, String price)
    {
        this.stock = stock;
        this.price = price;
    }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
