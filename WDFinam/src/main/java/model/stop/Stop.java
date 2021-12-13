package model.stop;

import model.BaseRequest;

import java.util.Objects;

public class Stop extends BaseRequest {
    private boolean enableStopLoss;
    private String stopLossPrice;

    private boolean enableTakeProfit;
    private String takeProfitPrice;

    public Stop(String company, String amount, boolean enableStopLoss, String stopLossPrice, boolean enableTakeProfit, String takeProfitPrice) {
        super(company, amount);
        this.enableStopLoss = enableStopLoss;
        this.stopLossPrice = stopLossPrice;
        this.enableTakeProfit = enableTakeProfit;
        this.takeProfitPrice= takeProfitPrice;
    }

    public Stop(String company, String amount, boolean enableStopLoss, boolean enableTakeProfit) {
        super(company, amount);
        this.enableStopLoss = enableStopLoss;
        this.enableTakeProfit = enableTakeProfit;
    }

    public boolean isEnableStopLoss() { return enableStopLoss; }
    public void setEnableStopLoss(boolean enableStopLoss) { this.enableStopLoss = enableStopLoss; }

    public String getStopLossPrice() { return stopLossPrice; }
    public void setStopLossPrice(String stopLossPrice) { this.stopLossPrice = stopLossPrice; }

    public boolean isEnableTakeProfit() { return enableTakeProfit; }
    public void setEnableTakeProfit(boolean enableTakeProfit) { this.enableTakeProfit = enableTakeProfit; }

    public String getTakeProfitPrice() { return takeProfitPrice; }
    public void setTakeProfitPrice(String takeProfitPrice) { this.takeProfitPrice = takeProfitPrice; }

    @Override
    public String toString() {
        return "Stop{" +
                "enableStopLoss=" + enableStopLoss +
                ", stopLossPrice='" + stopLossPrice + '\'' +
                ", enableTakeProfit=" + enableTakeProfit +
                ", takeProfitPrice='" + takeProfitPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return Objects.equals(company, stop.company) &&
                Objects.equals(amount, stop.amount) &&
                enableStopLoss == stop.enableStopLoss &&
                        enableTakeProfit == stop.enableTakeProfit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enableStopLoss, stopLossPrice, enableTakeProfit, takeProfitPrice);
    }
}
