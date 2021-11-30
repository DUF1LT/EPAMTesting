package model;

public class Stop {
    private boolean enableStopLoss;
    private String stopLossPrice;

    private boolean enableTakeProfit;
    private String takeProfitPrice;

    public Stop(boolean enableStopLoss, String stopLossPrice, boolean enableTakeProfit, String takeProfitPrice)
    {
        this.enableStopLoss = enableStopLoss;
        this.stopLossPrice = stopLossPrice;
        this.enableTakeProfit = enableTakeProfit;
        this.takeProfitPrice= takeProfitPrice;
    }

    public boolean isEnableStopLoss() { return enableStopLoss; }
    public void setEnableStopLoss(boolean enableStopLoss) { this.enableStopLoss = enableStopLoss; }

    public String getStopLossPrice() { return stopLossPrice; }
    public void setStopLossPrice(String stopLossPrice) { this.stopLossPrice = stopLossPrice; }

    public boolean isEnableTakeProfit() { return enableTakeProfit; }
    public void setEnableTakeProfit(boolean enableTakeProfit) { this.enableTakeProfit = enableTakeProfit; }

    public String getTakeProfitPrice() { return takeProfitPrice; }
    public void setTakeProfitPrice(String takeProfitPrice) { this.takeProfitPrice = takeProfitPrice; }
}
