package app;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Transcation{
    final Account sender;
    final Account recipient;
    final LocalDateTime transactionDate = LocalDateTime.now(ZoneId.of("CTT"));
    final TranscationType type;
    final double amount;
    

    Transcation(Account sender, Account recipient, TranscationType type){
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.amount = 0;
    }

    Transcation(Account sender, Account recipient, TranscationType type, double amount){
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.amount = amount;
    }

    
    @Override
    public String toString(){
        switch(this.type){
            case TranscationType.RECIEVE -> {
                return "Recieved " + this.amount + " from " + this.sender + " on " + this.transactionDate.toString();
            }
            case TranscationType.SEND -> {
                return "Sent " + this.amount + " to " + this.recipient + " on " + this.transactionDate.toString();
            }
            case TranscationType.CHECK_BALANCE -> {
                return "Checked Balance on " + this.transactionDate.toString();
            }
        }
        return "";
    }
}

enum TranscationType{
    SEND,
    RECIEVE,
    CHECK_BALANCE
}

