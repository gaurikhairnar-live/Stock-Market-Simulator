package stocksim; // package declaration

// Inheritance - Stock extends Asset, gets name, price, count from Asset
// Interface   - Stock implements Tradable, must provide buy() and sell()
public class Stock extends Asset implements Tradable {

    private String sector;   // Encapsulation - private field for sector e.g. "IT"
    private int quantity;    // Encapsulation - private field for units available in market

    // constructor - super() calls the Asset parent constructor
    public Stock(String name, double price, String sector, int quantity) {
        super(name, price);       // call parent class constructor (inheritance)
        this.sector = sector;     // 'this' to set current object's field
        this.quantity = quantity; // set available quantity
    }

    // Polymorphism - overrides abstract method from Asset
    // provides Stock-specific description, different from MutualFund
    @Override
    public String getDescription() {
        return "Sector: " + sector + " | Available: " + quantity; // stock specific info
    }

    // implements Tradable interface buy() method
    @Override
    public void buy(int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty); // custom exception for bad qty
        if (qty > quantity) throw new Exception("Only " + quantity + " units available."); // not enough stock
        quantity -= qty; // reduce available market quantity
        System.out.println("  Bought " + qty + " unit(s) of " + getName()); // confirm purchase
    }

    // Method overloading - same method name 'buy' but different parameters
    // this version accepts an extra note along with quantity
    public void buy(int qty, String note) throws Exception {
        buy(qty);                              // calls the buy(int) method above
        System.out.println("  Note: " + note); // print the extra note
    }

    // implements Tradable interface sell() method
    @Override
    public void sell(int qty) throws Exception {
        if (qty < Asset.MIN_QTY) throw new InvalidQuantityException(qty); // custom exception for bad qty
        quantity += qty; // stock returns to the market
        System.out.println("  Sold " + qty + " unit(s) of " + getName()); // confirm sale
    }

    // getter - encapsulation
    public String getSector() { return sector; }

    // getter - encapsulation
    public int getQuantity() { return quantity; }

    // setter - encapsulation
    public void setQuantity(int quantity) { this.quantity = quantity; }
}