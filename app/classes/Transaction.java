package app.classes;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Transaction{
    final Account sender;
    final Account recipient;
    final LocalDateTime transactionDate = LocalDateTime.now(ZoneId.of("CTT"));
    final TransactionType type;
    final double amount;
    

    Transaction(Account sender, Account recipient, TransactionType type){
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.amount = 0;
    }

    Transaction(Account sender, Account recipient, TransactionType type, double amount){
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.amount = amount;
    }

    
    @Override
    public String toString(){
        switch(this.type){
            case TransactionType.RECIEVE -> {
                return "Recieved " + this.amount + " from " + this.sender + " on " + this.transactionDate.toString();
            }
            case TransactionType.SEND -> {
                return "Sent " + this.amount + " to " + this.recipient + " on " + this.transactionDate.toString();
            }
            case TransactionType.CHECK_BALANCE -> {
                return "Checked Balance on " + this.transactionDate.toString();
            }
        }
        return "";
    }
}
