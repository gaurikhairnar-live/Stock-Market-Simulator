package stocksim; // package groups all project files together

// interface = a contract, any class that implements this MUST have buy() and sell()
public interface Tradable {

    // no body here, just the method signature
    // 'throws Exception' means the method is allowed to throw an error
    void buy(int quantity) throws Exception;

    // any class implementing Tradable must also provide a sell() method
    void sell(int quantity) throws Exception;
}