package stocksim; // package declaration

import java.util.Scanner; // Scanner reads keyboard input from user

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // sc reads user keyboard input
        Market market = new Market();        // create the market with all stocks

        System.out.println("=================================");
        System.out.println("   STOCK MARKET SIMULATOR");
        System.out.println("=================================");

        // taking user details as input at runtime
        System.out.print("Enter your name      : ");
        String userName = sc.nextLine(); // read username from user

        System.out.print("Enter your account ID: ");
        String accountId = sc.nextLine(); // read account ID from user

        System.out.print("Enter starting balance: Rs.");
        double balance = Double.parseDouble(sc.nextLine()); // read balance from user

        // POLYMORPHISM + DYNAMIC METHOD DISPATCH
        // BaseAccount is abstract, UserAccount is the actual object
        // Java decides at runtime which showSummary() to call - this is Dynamic Method Dispatch
       
       UserAccount user = new UserAccount(userName, accountId, balance);// easy way to create user account object

        // INTERFACE USAGE
        // Tradable interface is implemented by Stock and MutualFund
        // both are forced to have buy() and sell() because of the interface contract

        // INHERITANCE
        // Stock extends Asset, MutualFund extends Asset
        // UserAccount extends BaseAccount
        // all custom exceptions extend Exception

        // ENCAPSULATION
        // all fields in every class are private
        // accessed only through getters and setters

        // ABSTRACTION
        // Asset is abstract - cannot create 'new Asset()' directly
        // BaseAccount is abstract - cannot create 'new BaseAccount()' directly

        // STATIC AND FINAL
        // Asset.MIN_QTY is a final constant - value never changes
        // Asset.getCount() is a static method - shared across all Asset objects

        // METHOD OVERLOADING
        // Stock has two buy() methods - buy(int qty) and buy(int qty, String note)
        // same method name, different parameters = overloading

        System.out.println("\nWelcome, " + userName + "!\n");

        boolean running = true; // loop control flag

        while (running) {

            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Buy Mutual Fund");
            System.out.println("5. Sell Mutual Fund");
            System.out.println("6. My Account");
            System.out.println("7. Update Prices");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            try { // outer try-catch handles invalid menu input
                int choice = Integer.parseInt(sc.nextLine()); // NumberFormatException if user types letters

                if (choice == 1) {
                    market.showMarket(); // show all stocks and mutual funds

                } else if (choice == 2) { // BUY STOCK

                    System.out.print("Stock name: ");
                    String name = sc.nextLine();

                    try { // stock is validated FIRST before asking quantity
                        Stock stock = market.findStock(name); // throws StockNotFoundException if invalid

                        System.out.print("Quantity  : "); // only reached if stock name is valid
                        int qty = Integer.parseInt(sc.nextLine()); // NumberFormatException if not a number

                        user.buyStock(stock, qty); // throws InsufficientFundsException or InvalidQuantityException
                        System.out.println("Buy successful!\n");

                    } catch (StockNotFoundException e) {     // custom exception - stock not in market
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InsufficientFundsException e) { // custom exception - not enough balance
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InvalidQuantityException e) {   // custom exception - qty is 0 or negative
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (NumberFormatException e) {      // built-in exception - letters typed for quantity
                        System.out.println("ERROR: Please enter a valid number.\n");
                    } catch (Exception e) {                  // general catch - any other unexpected error
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } finally {                              // finally always runs whether exception occurred or not
                        System.out.println("[Buy attempt done]\n");
                    }

                } else if (choice == 3) { // SELL STOCK

                    System.out.print("Stock name: ");
                    String name = sc.nextLine();

                    try { // stock is validated FIRST before asking quantity
                        Stock stock = market.findStock(name); // throws StockNotFoundException if invalid

                        System.out.print("Quantity  : "); // only reached if stock name is valid
                        int qty = Integer.parseInt(sc.nextLine()); // NumberFormatException if not a number

                        user.sellStock(stock, qty); // throws InvalidQuantityException or general Exception
                        System.out.println("Sell successful!\n");

                    } catch (StockNotFoundException e) {   // custom exception - stock not in market
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InvalidQuantityException e) { // custom exception - bad quantity
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (NumberFormatException e) {    // built-in exception - letters typed for quantity
                        System.out.println("ERROR: Please enter a valid number.\n");
                    } catch (Exception e) {                // general catch - e.g. not enough units owned
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } finally {                            // finally always runs
                        System.out.println("[Sell attempt done]\n");
                    }

                } else if (choice == 4) { // BUY MUTUAL FUND

                    System.out.print("Mutual Fund name: ");
                    String name = sc.nextLine();

                    try { // fund is validated FIRST before asking quantity
                        MutualFund mf = market.findMutualFund(name); // throws StockNotFoundException if invalid

                        System.out.print("Quantity  : "); // only reached if fund name is valid
                        int qty = Integer.parseInt(sc.nextLine()); // NumberFormatException if not a number

                        user.buyMutualFund(mf, qty); // throws InsufficientFundsException or InvalidQuantityException
                        System.out.println("Mutual Fund purchase successful!\n");

                    } catch (StockNotFoundException e) {     // custom exception - fund not in market
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InsufficientFundsException e) { // custom exception - not enough balance
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InvalidQuantityException e) {   // custom exception - qty is 0 or negative
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (NumberFormatException e) {      // built-in exception - letters typed for quantity
                        System.out.println("ERROR: Please enter a valid number.\n");
                    } catch (Exception e) {                  // general catch - any other unexpected error
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } finally {                              // finally always runs
                        System.out.println("[Mutual Fund buy attempt done]\n");
                    }

                } else if (choice == 5) { // SELL MUTUAL FUND

                    System.out.print("Mutual Fund name: ");
                    String name = sc.nextLine();

                    try { // fund is validated FIRST before asking quantity
                        MutualFund mf = market.findMutualFund(name); // throws StockNotFoundException if invalid

                        System.out.print("Quantity  : "); // only reached if fund name is valid
                        int qty = Integer.parseInt(sc.nextLine()); // NumberFormatException if not a number

                        user.sellMutualFund(mf, qty); // throws InvalidQuantityException or general Exception
                        System.out.println("Mutual Fund sale successful!\n");

                    } catch (StockNotFoundException e) {   // custom exception - fund not in market
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (InvalidQuantityException e) { // custom exception - bad quantity
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } catch (NumberFormatException e) {    // built-in exception - letters typed for quantity
                        System.out.println("ERROR: Please enter a valid number.\n");
                    } catch (Exception e) {                // general catch - e.g. not enough units owned
                        System.out.println("ERROR: " + e.getMessage() + "\n");
                    } finally {                            // finally always runs
                        System.out.println("[Mutual Fund sell attempt done]\n");
                    }

                } else if (choice == 6) {
                    // DYNAMIC METHOD DISPATCH in action
                    // account is declared as BaseAccount but calls UserAccount's showSummary()
                    account.showSummary();

                } else if (choice == 7) {
                    market.fluctuatePrices(); // randomly updates all stock and fund prices

                } else if (choice == 8) {
                    running = false; // set flag to false, exits the while loop
                    System.out.println("Goodbye, " + userName + "!");

                } else {
                    System.out.println("Enter a number between 1 and 8.\n"); // invalid menu option
                }

            } catch (NumberFormatException e) { // built-in exception - letters typed instead of number
                System.out.println("ERROR: Please enter a valid number.\n");
            }
        }

        sc.close(); // close scanner to free resources
    }
}