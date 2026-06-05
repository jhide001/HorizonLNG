import java.util.ArrayList;
import java.util.HashMap;

public class DealRepository {
    private ArrayList<EndurDeal> deals;
    private HashMap<Integer, EndurDeal> dealMap;

    public DealRepository() {
        deals = new ArrayList<>();
        dealMap = new HashMap<>();
    }
    public void addDeal(EndurDeal deal) {
        deals.add(deal);
        dealMap.put(deal.getTranNum(), deal);
    }

    public ArrayList<EndurDeal> getDeals() {
        return deals;
    }
    public EndurDeal findByTranNum(int tranNum) {
        return dealMap.get(tranNum);
    }
    public int getDealCount() {
        return deals.size();


}

}
