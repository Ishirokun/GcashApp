package app;

import java.util.Scanner;

public class CLI {
    public static Scanner input = new Scanner(System.in);

    /**
     * requestString: takes an input line from user in the command line interface with a message.
     * @param message the message for the user.
     * @return String user-input.
     */
    public static String requestString(String message){
        System.out.println(message);
        return input.nextLine().trim();
    }
}
