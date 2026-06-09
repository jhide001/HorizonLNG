public class PhysicalDeal extends EndurDeal {
    private String deliveryPoint;

    public PhysicalDeal(int tranNum, TranStatus tranStatus, double dealPrice,
                        String counterparty, String commodity, double volume,
                        String deliveryPoint) {
        super(tranNum, tranStatus, dealPrice, counterparty, commodity, volume);
        this.deliveryPoint = deliveryPoint;
    }

    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    public String toString() {
        return super.toString() + " | Delivery: " + deliveryPoint;
    }
}