package app;

import java.util.ArrayList;

import app.classes.Name;


public class Account{
    final String id;
    final private Name name;
    final private String password;

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private double balance;
    private boolean isLoggedIn;
    

    /**
     * Creates an Account object statically from the class method.
     * 
     * @param id
     * @param password
     * @param name
     * @return
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
        this.transactions.add(new Transcation(sender, this, TranscationType.RECIEVE, amount));
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