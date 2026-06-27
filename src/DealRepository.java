import java.io.BufferedReader;
import java.io.FileReader;
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

    public ArrayList<EndurDeal> getActiveDeals() {
        ArrayList<EndurDeal> activeDeals = new ArrayList<>();
        for (EndurDeal deal : deals) {
            if (deal.isActive()) {
                activeDeals.add(deal);
            }

        }

        return activeDeals;
    }

    public ArrayList<EndurDeal> findByCounterparty(String counterparty) {
        ArrayList<EndurDeal> result = new ArrayList<>();
        for (EndurDeal deal : deals) {
            if (deal.getCounterparty().equals(counterparty)) {
                result.add(deal);
            }
        }
        return result;
    }
    public double getTotalMTM(double marketPrice) {
        double total = 0.0;
        for (EndurDeal deal : deals) {
            if (deal.isActive()) {
                total = total + deal.calculateMTM(marketPrice);
            }
        }
        return total;
    }
    public void loadDealsFromCSV(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            reader.readLine();  // skip the header row

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int tranNum = Integer.parseInt(parts[0].trim());
                TranStatus status = TranStatus.valueOf(parts[1].trim());
                double dealPrice = Double.parseDouble(parts[2].trim());
                String counterparty = parts[3].trim();
                String commodity = parts[4].trim();
                double volume = Double.parseDouble(parts[5].trim());

                EndurDeal deal = new EndurDeal(tranNum, status, dealPrice,
                        counterparty, commodity, volume);
                addDeal(deal);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load CSV: " + e.getMessage());
        }
    }
}





