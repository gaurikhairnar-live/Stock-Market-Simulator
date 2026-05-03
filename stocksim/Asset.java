package stocksim; // package declaration

// 'abstract' = this class cannot be used with 'new Asset()'
// it is only a parent class for Stock and MutualFund
public abstract class Asset {

    private String name;  // Encapsulation - private field for asset name
    private double price; // Encapsulation - private field for asset price

    // static = one shared copy across ALL objects, not per object
    private static int count = 0; // counts how many Asset objects have been created

    // final = this value can NEVER be changed after it is set
    public static final int MIN_QTY = 1; // minimum units allowed in any trade

    // constructor - called by child classes using super(name, price)
    public Asset(String name, double price) {
        this.name = name;   // 'this.name' = the field, 'name' = the parameter
        this.price = price; // set price field
        count++;            // increment shared counter each time an asset is created
    }

    // abstract method - no body here
    // every subclass MUST provide its own version of this method
    public abstract String getDescription();

    // getter for name - encapsulation
    public String getName() { return name; }

    // setter for name - encapsulation
    public void setName(String name) { this.name = name; }

    // getter for price - encapsulation
    public double getPrice() { return price; }

    // setter for price - encapsulation, used during price fluctuation
    public void setPrice(double price) { this.price = price; }

    // static method - called as Asset.getCount(), not on any object
    public static int getCount() { return count; }

    // Polymorphism - overrides toString() from Object class
    @Override
    public String toString() {
        // getDescription() calls the subclass version at runtime (polymorphism)
        return name + " | Rs." + price + " | " + getDescription();
    }
}