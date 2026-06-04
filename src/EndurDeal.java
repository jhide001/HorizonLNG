import java.util.ArrayList;

public class EndurDeal {
    private int tranNum;
    private String tranStatus;
    private double dealPrice;
    private String counterparty;
    private String commodity;

    public EndurDeal(int tranNum, String tranStatus, double dealPrice,
                     String counterparty, String commodity) {
        this.tranNum = tranNum;
        this.tranStatus = tranStatus;
        this.dealPrice = dealPrice;
        this.counterparty = counterparty;
        this.commodity = commodity;
    }

    public int getTranNum() { return tranNum; }
    public String getTranStatus() { return tranStatus; }
    public double getDealPrice() { return dealPrice; }
    public String getCounterparty() { return counterparty; }
    public String getCommodity() { return commodity; }

    public boolean isActive() {
        return tranStatus.equals("Validated");
    }

    public double calculateMTM(double marketPrice) {
        if (isActive()) {
            return marketPrice - dealPrice;
        } else {
            return 0.0;
        }
    }

    public String toString() {
        return "Deal: " + tranNum
                + " | " + counterparty
                + " | " + commodity
                + " | Status: " + tranStatus
                + " | Price: $" + dealPrice;
    }
}