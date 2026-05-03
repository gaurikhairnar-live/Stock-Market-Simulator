package stocksim; // package declaration

import java.util.ArrayList; // ArrayList to hold all market assets
import java.util.Random;    // Random for simulating price changes

public class Market {

    // Encapsulation - private ArrayList stores all stocks and mutual funds
    // ArrayList<Asset> uses the abstract parent type to hold both Stock and MutualFund
    // this is runtime polymorphism - same list holds different subclass objects
    private ArrayList<Asset> assets;

    // constructor - initializes the market and loads default stocks
    public Market() {
        assets = new ArrayList<>(); // create empty list
        loadAssets();               // fill with default data
    }

    // private method - adds default stocks and mutual funds to the market
    private void loadAssets() {
        assets.add(new Stock("RELIANCE", 2500.0, "Energy",  100)); // add Stock objects
        assets.add(new Stock("TCS",      3800.0, "IT",       80)); // add Stock objects
        assets.add(new Stock("HDFC",     1600.0, "Banking", 120)); // add Stock objects
        assets.add(new Stock("WIPRO",     480.0, "IT",      150)); // add Stock objects
        assets.add(new MutualFund("SBI_BLUECHIP", 55.0, "Equity")); // add MutualFund objects
        assets.add(new MutualFund("HDFC_DEBT",    30.0, "Debt"));   // add MutualFund objects
    }

    // display all assets in the market
    public void showMarket() {
        System.out.println("\n======= MARKET =======");
        for (int i = 0; i < assets.size(); i++) {                 // loop through all assets
            System.out.println((i + 1) + ". " + assets.get(i));  // toString() calls subclass version (polymorphism)
        }
        System.out.println("Total assets: " + Asset.getCount()); // static method call on class
        System.out.println("======================\n");
    }

    // find a stock by name - throws exception if not found
    public Stock findStock(String name) throws StockNotFoundException {
        for (Asset a : assets) {                                    // loop through all assets
            if (a instanceof Stock) {                               // check if it is a Stock
                Stock s = (Stock) a;                               // downcast Asset to Stock
                if (s.getName().equalsIgnoreCase(name)) return s;  // return if found
            }
        }
        throw new StockNotFoundException(name); // not found - throw custom exception
    }

    // find a mutual fund by name - throws exception if not found
    public MutualFund findMutualFund(String name) throws StockNotFoundException {
        for (Asset a : assets) {                                    // loop through all assets
            if (a instanceof MutualFund) {                         // check if it is a MutualFund
                MutualFund mf = (MutualFund) a;                    // downcast Asset to MutualFund
                if (mf.getName().equalsIgnoreCase(name)) return mf; // return if found
            }
        }
        throw new StockNotFoundException(name); // not found - throw custom exception
    }

    // randomly change all asset prices to simulate market movement
    public void fluctuatePrices() {
        Random r = new Random(); // create Random object
        System.out.println("\n-- Price Update --");
        for (Asset a : assets) {                                                                    // loop through all assets
            double old = a.getPrice();                                                              // get current price
            double newPrice = Math.round((old + old * (r.nextDouble() - 0.5) * 0.1) * 100.0) / 100.0; // -5% to +5% change
            a.setPrice(newPrice);                                                                   // update price via setter
            System.out.println(a.getName() + ": Rs." + old + " -> Rs." + newPrice);               // show change
        }
        System.out.println("------------------\n");
    }

    // getter - encapsulation
    public ArrayList<Asset> getAssets() { return assets; }
}