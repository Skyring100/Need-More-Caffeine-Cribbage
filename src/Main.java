package src;

import java.util.Scanner;

public class Main {
    protected static String inputName;
    public static Scanner input  = new Scanner(System.in);
    public static void main(String[] args) {
        enterName();
        new Game(new Player(inputName));
    }

    /**
     * Prompts the user to enter their name
     * Will only accept names between 1 and 20 in length
     */
    private static String enterName() {
        do {
            System.out.print("Enter Name: ");
            inputName = input.nextLine().trim();
        } while (!((inputName.length() > 0) && (inputName.length() < 20)));
        return inputName;
    }
    //eclipse sucks

}
