package stocksim; // package declaration

import java.util.ArrayList; // import ArrayList to store owned stocks and funds

// Inheritance - UserAccount extends BaseAccount, gets userName and accountId
// Interface   - implements Tradable, must provide buy() and sell()
public class UserAccount extends BaseAccount implements Tradable {

    private double balance;                // Encapsulation - user's current money
    private ArrayList<String> ownedNames;  // Encapsulation - names of stocks/funds owned
    private ArrayList<Integer> ownedQtys;  // Encapsulation - matching quantities

    // constructor - calls parent using super()
    public UserAccount(String userName, String accountId, double balance) {
        super(userName, accountId);           // call BaseAccount constructor (inheritance)
        this.balance = balance;              // set balance using 'this'
        this.ownedNames = new ArrayList<>();  // start with empty list
        this.ownedQtys  = new ArrayList<>();  // start with empty list
    }

    // Polymorphism - overrides abstract method from BaseAccount
    @Override
    public void showSummary() {
        System.out.println("\n====== ACCOUNT SUMMARY ======");
        System.out.println("Name    : " + getUserName());   // getUserName() from parent class
        System.out.println("ID      : " + getAccountId()); // getAccountId() from parent class
        System.out.println("Balance : Rs." + balance);     // show current balance
        System.out.println("--- Portfolio ---");
        if (ownedNames.isEmpty()) {                         // check if portfolio is empty
            System.out.println("  (No assets owned yet)");
        } else {
            for (int i = 0; i < ownedNames.size(); i++) {  // loop through owned assets
                System.out.println("  " + ownedNames.get(i) + " -> " + ownedQtys.get(i) + " unit(s)");
            }
        }
        System.out.println("=============================\n");
    }

    // implements Tradable interface buy() - basic version
    @Override
    public void buy(int qty) throws Exception {
        if (qty < 1) throw new InvalidQuantityException(qty); // custom exception for bad qty
        System.out.println("  Use buyStock() or buyMutualFund() for full purchase.");
    }

    // implements Tradable interface sell() - basic version
    @Override
    public void sell(int qty) throws Exception {
        if (qty < 1) throw new InvalidQuantityException(qty); // custom exception for bad qty
        System.out.println("  Use sellStock() or sellMutualFund() for full sale.");
    }

    // full buy flow for stocks
    public void buyStock(Stock stock, int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty);                           // check quantity
        double cost = stock.getPrice() * qty;                                                        // calculate total cost
        if (cost > balance) throw new InsufficientFundsException("Not enough balance.", cost - balance); // check balance
        stock.buy(qty);   // call Stock's buy() which reduces market quantity
        balance -= cost;  // deduct cost from user balance
        addToPortfolio(stock.getName(), qty); // update portfolio
        System.out.println("  Total spent: Rs." + cost + " | Remaining balance: Rs." + balance);
    }

    // full sell flow for stocks
    public void sellStock(Stock stock, int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty);          // check quantity
        int index = ownedNames.indexOf(stock.getName());                            // find in portfolio
        if (index == -1) throw new StockNotFoundException(stock.getName());         // not owned
        int owned = ownedQtys.get(index);                                           // how many owned
        if (qty > owned) throw new Exception("You own only " + owned + " unit(s) of " + stock.getName()); // not enough
        stock.sell(qty);                       // call Stock's sell()
        double earned = stock.getPrice() * qty; // calculate earnings
        balance += earned;                     // add to balance
        removeFromPortfolio(index, qty, owned); // update portfolio
        System.out.println("  Total earned: Rs." + earned + " | New balance: Rs." + balance);
    }

    // full buy flow for mutual funds
    public void buyMutualFund(MutualFund mf, int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty);                          // check quantity
        double cost = mf.getPrice() * qty;                                                          // calculate cost
        if (cost > balance) throw new InsufficientFundsException("Not enough balance.", cost - balance); // check balance
        mf.buy(qty);      // call MutualFund's buy() method
        balance -= cost;  // deduct from balance
        addToPortfolio(mf.getName(), qty); // update portfolio
        System.out.println("  Total spent: Rs." + cost + " | Remaining balance: Rs." + balance);
    }

    // full sell flow for mutual funds
    public void sellMutualFund(MutualFund mf, int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty);         // check quantity
        int index = ownedNames.indexOf(mf.getName());                              // find in portfolio
        if (index == -1) throw new StockNotFoundException(mf.getName());           // not owned
        int owned = ownedQtys.get(index);                                          // how many owned
        if (qty > owned) throw new Exception("You own only " + owned + " unit(s) of " + mf.getName()); // not enough
        mf.sell(qty);                         // call MutualFund's sell()
        double earned = mf.getPrice() * qty;  // calculate earnings
        balance += earned;                    // add to balance
        removeFromPortfolio(index, qty, owned); // update portfolio
        System.out.println("  Total earned: Rs." + earned + " | New balance: Rs." + balance);
    }

    // helper - adds asset to portfolio after buying
    private void addToPortfolio(String name, int qty) {
        int index = ownedNames.indexOf(name); // check if already owned
        if (index == -1) {
            ownedNames.add(name); // new asset - add to list
            ownedQtys.add(qty);   // add matching quantity
        } else {
            ownedQtys.set(index, ownedQtys.get(index) + qty); // increase existing quantity
        }
    }

    // helper - removes or reduces asset in portfolio after selling
    private void removeFromPortfolio(int index, int qty, int owned) {
        if (qty == owned) {
            ownedNames.remove(index); // sold everything - remove from list
            ownedQtys.remove(index);  // remove matching quantity
        } else {
            ownedQtys.set(index, owned - qty); // reduce quantity
        }
    }

    // getter - encapsulation
    public double getBalance() { return balance; }

    // setter - encapsulation
    public void setBalance(double balance) { this.balance = balance; }
}