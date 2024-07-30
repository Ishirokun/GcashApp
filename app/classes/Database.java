package app.classes;

import app.exceptions.ExistingAccountException;
import java.util.ArrayList;
import java.util.Optional;


public class Database{
    ArrayList<Account> accountList = new ArrayList<>();

    public boolean accountExists(String id) {
        return getAccount(id).isPresent();
    }

    public Account registerAccount(String id, String password, Name name) throws ExistingAccountException{
        Optional<Account> check = getAccount(id);
        if (check.isPresent()) throw new ExistingAccountException();
        Account result = Account.createAccount(id, password, name);
        accountList.add(result);
        return result;
    }

    public Optional<Account> getAccount(String id) {
        Optional<Account> result = accountList.stream()
                                    .filter(x -> x.id.equals(id))
                                    .findFirst();
        return result; 
    }

}
