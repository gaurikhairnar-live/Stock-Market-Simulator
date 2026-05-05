package stocksim; // package declaration

// Inheritance - our custom exception extends the built-in Exception class
public class InvalidQuantityException extends Exception {

    private int qty; // Encapsulation - private field stores the bad quantity entered

    // constructor takes the invalid quantity
    public InvalidQuantityException(int qty) { // take quantity  as parameter
        super("Invalid quantity: " + qty); // calls Exception's constructor with our message
        this.qty = qty;                    // 'this.qty' = field, 'qty' = parameter
    }

    // Polymorphism - overrides getMessage() from parent Exception class
    @Override
    public String getMessage() 
    {
        return super.getMessage() + ". Quantity must be 1 or more.";
         // super fetches parent message that means with invalid quantity and this message Quantity must be 1 or more
    }

    // getter - controlled read access to private field (encapsulation)
    public int getQty() 
    {
        return qty; // return the stored bad quantity
    }
}
