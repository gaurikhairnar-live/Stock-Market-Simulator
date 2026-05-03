package stocksim; // package declaration

// Inheritance - MutualFund extends Asset
// Interface   - implements Tradable
public class MutualFund extends Asset implements Tradable {

    private String fundType; // Encapsulation - private field for fund type e.g. "Equity"

    // constructor - super() calls the Asset parent constructor
    public MutualFund(String name, double price, String fundType) {
        super(name, price);        // call Asset constructor (inheritance)
        this.fundType = fundType;  // set fund type using 'this'
    }

    // Polymorphism - overrides abstract method from Asset
    // different description compared to Stock's getDescription()
    @Override
    public String getDescription() {
        return "Mutual Fund | Type: " + fundType; // mutual fund specific info
    }

    // implements Tradable interface buy() method
    @Override
    public void buy(int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty); // custom exception
        System.out.println("  Bought " + qty + " unit(s) of " + getName()); // confirm purchase
    }

    // implements Tradable interface sell() method
    @Override
    public void sell(int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty); // custom exception
        System.out.println("  Sold " + qty + " unit(s) of " + getName()); // confirm sale
    }

    // getter - encapsulation
    public String getFundType() { return fundType; }

    // setter - encapsulation
    public void setFundType(String fundType) { this.fundType = fundType; }
}