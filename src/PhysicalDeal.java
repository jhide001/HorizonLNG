public class PhysicalDeal extends EndurDeal {
    private String deliveryPoint;
    private double shippingCost;

    public PhysicalDeal(int tranNum, TranStatus tranStatus, double dealPrice,
                        String counterparty, String commodity, double volume,
                        String deliveryPoint, double shippingCost) {
        super(tranNum, tranStatus, dealPrice, counterparty, commodity, volume);
        this.deliveryPoint = deliveryPoint;
        this.shippingCost = shippingCost;
    }

    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    @Override
    public double calculateMTM(double marketPrice) {
        if (isActive()) {
            return (marketPrice - getDealPrice() - shippingCost) * getVolume();
        } else {
            return 0.0;
        }
    }

    public String toString() {
        return super.toString()
                + " | Delivery: " + deliveryPoint
                + " | Shipping: $" + shippingCost;
    }
}