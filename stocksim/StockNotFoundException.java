package stocksim; // package declaration

// Inheritance - extends built-in Exception class
public class StockNotFoundException extends Exception {

    private String stockName; // Encapsulation - stores the stock name that was not found

    // constructor takes the stock name that was searched but not found
    public StockNotFoundException(String stockName) {
        super("Stock not found: " + stockName); // pass message to Exception
        this.stockName = stockName;             // store using 'this'
    }

    // Polymorphism - overrides getMessage() from Exception
    @Override
    public String getMessage() {
        return super.getMessage() + ". Please check the name and try again."; // adds helpful hint
    }

    // getter - encapsulation
    public String getStockName() {
        return stockName; // return the name that was not found
    }
}