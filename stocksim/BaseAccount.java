package stocksim; // package declaration

// 'abstract' = cannot do 'new BaseAccount()', only subclasses can be created
public abstract class BaseAccount {

    private String userName;  // Encapsulation - private field for account holder name
    private String accountId; // Encapsulation - private field for unique account ID

    // constructor - called by subclass using super()
    public BaseAccount(String userName, String accountId) {
        this.userName = userName;   // 'this' to refer to the field, not the parameter
        this.accountId = accountId; // set account ID
    }

    // abstract method - no body here
    // subclass MUST override this to show account details
    public abstract void showSummary();

    // getter - encapsulation
    public String getUserName() { return userName; }

    // getter - encapsulation
    public String getAccountId() { return accountId; }

    // Polymorphism - overrides toString() from Object class
    @Override
    public String toString() {
        return "Account[" + accountId + "] - " + userName; // readable account info
    }
}