package model;

public abstract  class BaseRequest {
    protected String company;
    protected String amount;

    public BaseRequest(String company, String amount) {
        this.company = company;
        this.amount = amount;
    }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
}
