package app.classes;

import java.util.ArrayList;

import app.classes.Transaction;
import app.classes.TransactionType;
import app.exceptions.NonNumericPinException;
import app.exceptions.PasswordLenghtException;


public class Account{
    final String id;
    final private Name name;
    final private String password;

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private double balance;
    private boolean isLoggedIn;
    

    /**
     * Creates an Account object statically from the class method.
     * The account is then automatically sent into the database.
     * 
     * @param id Unique Identifier for Account, typically the mobile number.
     * @param password The new account's password, used for login authentication. Must be 4-digits.
     * @param name Name object that contains the full name of the user.
     * @return Account object reference
     */
    public static Account createAccount(String id, String password, Name name) {
        if (!password.matches("[0-9]+")) throw new NonNumericPinException();
        if (password.length() != 4) throw new PasswordLenghtException();
        return new Account(id, password, name);
    }

    /**
     * Authenticates the account, accesses the private password, and authenticated status.
     * @param password 4-digit value to test for the account's password.
     * @return true or false if the login is successful.
     */
    public boolean login(String password) {
        if (!password.matches("[0-9]+")) throw new NonNumericPinException();
        if (password.length() != 4) throw new PasswordLenghtException();
        if (password.equals(this.password)){
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    /**
     * Logouts the account, changes the authentication boolean to false.
     */
    public void logout(){
        this.isLoggedIn = false;
    }

    /**
     * Read-only boolean if the account is logged on.
     * @return true or false
     */
    public boolean isAuthenticated(){
        return this.isLoggedIn;
    }

    /**
     * Sends the money from the account to another.
     * @param recepient Account object to send the money to.
     * @param amount of money to send.
     *
     */
    public Transaction sendMoney(Account recepient, double amount){
        if(this.isLoggedIn && this.balance > amount){
            this.balance -= amount;
            recepient.recieveMoney(this, amount);
        }
        Transaction transaction = new Transaction(this, recepient, TransactionType.SEND, amount);
        this.transactions.add(transaction);
        return transaction;
    }

    public Transaction recieveMoney(Account sender, double amount) {
        this.balance += amount;
        Transaction transaction = new Transaction(sender, this, TransactionType.RECIEVE, amount);
        this.transactions.add(transaction);
        return transaction;
    }

    public double getBalance(){
        this.transactions.add(new Transaction(this, this, TransactionType.CHECK_BALANCE));
        return this.balance;
    }


    private Account(String id, String password, Name name){
        this.id = id;
        this.password = password;
        this.balance = 0;
        this.name = name;
    }

    @Override
    public String toString(){
        return this.id + " - " + this.name;
    }

}