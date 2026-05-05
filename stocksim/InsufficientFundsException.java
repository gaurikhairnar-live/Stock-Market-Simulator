package stocksim; // package declaration

// Inheritance - extends built-in Exception class
public class InsufficientFundsException extends Exception {

    private double shortfall; // Encapsulation - stores how much money the user is missing

    // constructor takes a message and the missing amount
    public InsufficientFundsException(String message, double shortfall) {
        super(message);               // pass message up to Exception class
        this.shortfall = shortfall;   // store shortfall using 'this'
    }

    // Polymorphism - overrides getMessage() from parent Exception class
    @Override
    public String getMessage() {
        return super.getMessage() + " | Short by: Rs." + shortfall; // adds shortfall info
    }

    // getter - encapsulation
    public double getShortfall() {
        return shortfall; // return the stored shortfall value
    }
}
