package app;
import app.classes.Account;
import app.classes.Database;
import app.exceptions.ExistingAccountException;
import app.exceptions.NonNumericPinException;
import app.classes.Name;

import java.util.Optional;
import java.util.Scanner;

public class App{

    Database db = new Database();
    Scanner input = new Scanner(System.in);
    Account user;

    private void userMenu(){

    }

    private void login(){
        while(true){
            System.out.println("Type 0 to return to main menu\nYour mobile number:");
            String x = input.nextLine().trim();
            if (x.equals("0")) {
                return;
            } else {
                Optional<Account> account = db.getAccount(x);
                if (account.isPresent()) {
                    user = account.get();
                    System.out.println("Enter your 4-digit pin.");
                    while (true) {
                        String pin = input.nextLine().trim();
                        if (pin.equals("0")) return;
                        if (user.login(pin)) {
                                userMenu();
                                return;
                        }
                    }

                } else {
                    System.out.println("Mobile number is not registered.");
                }
            }
        }
    }

    private void register(){
        while(true){
            System.out.println("Type 0 to return to main menu\nYour mobile number:");
            String id = input.nextLine().trim();
            if (id.equals("0")) {
                return;
            } else {
                try {
                    if (db.accountExists(id)) throw new ExistingAccountException();
                    while (true) {
                        System.out.println("Enter 0 to return to main menu\nEnter a 4-digit pin password for your account : ");
                        String pin = input.nextLine().trim();
                        if (pin.equals("0")) return;
                        try {
                            String firstName = CLI.requestString("Enter your first name : ");
                            String lastName = CLI.requestString("Enter your last name : ");
                            String middleName = CLI.requestString("Enter your middle name : ");
                            Name name = new Name(firstName, lastName, middleName);
                            user = db.registerAccount(id, pin, name);
                            return;
                        } catch (NonNumericPinException e) {
                            System.out.println("Please enter a 4 digit pin.");
                        }
                    }
                } catch (ExistingAccountException e) {
                    System.out.println("The entered mobile number already exists");
                }
            }
        }
    }

    private void openMenu() {
        System.out.println("TOTALLY REAL AND LEGIT GCASH APP");
        while(true){
            System.out.println("1 - Login, 2 - Register");
            switch (input.nextLine().trim()) {
                case "1" -> {
                    login();
                }
                case "2" -> {
                    register();
                }
            }
            if (user != null) {
                if(user.isAuthenticated()){
                    while (true) { 
                        System.out.println("1 - Send Money\n2 - Check Balance\n3 - View Transactions\n4 - Logout");
                    }}
                }
            }
        }

        public static void main(String[] args) {
            App application = new App();
            application.openMenu();
        }
    }
