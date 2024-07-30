package app.classes;

import java.util.ArrayList;

import app.classes.Transaction;
import app.classes.TransactionType;


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
     * @param password The new account's password, used for login authentication.
     * @param name Name object that contains the full name of the user.
     * @return Account object reference
     */
    public static Account createAccount(String id, String password, Name name){
        return new Account(id, password, name);
    }
    
    public boolean login(String password){
        if (password.equals(this.password)){
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    public void logout(){
        this.isLoggedIn = false;
    }

    public boolean isAuthenticated(){
        return this.isLoggedIn;
    }
    
    public void sendMoney(Account recepient, double amount){
        if(this.isLoggedIn && this.balance > amount){
            this.balance -= amount;
            recepient.recieveMoney(this, amount);
        }
    }

    public void recieveMoney(Account sender, double amount) {
        this.transactions.add(new Transaction(sender, this, TransactionType.RECIEVE, amount));
        this.balance += amount;
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