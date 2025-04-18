package org.example.extras;
import java.util.Scanner;

/**
 * @class ConsoleUtils
 * @brief Class that provides methods for manipulating the console.
 */
public class Outools {

    // Defining ANSI codes for colors
    public static final String RESET = "\033[0m";
    public static final String BLACK= "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    /**
     *
     * Method to print colored text to the console.
     * @param color ANSI color code.
     * @param text Text to print.
     */
    public static void printlnf(String color, String text){
        System.out.print(color + text + RESET);
    }

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Generic method for capturing and validating user input.
     *
     * @param variable Reference to the object that will store the value (passed by generic type).
     * @param type     Type of data to capture ("int", "double", "string", "boolean").
     * @param <T>      Generic data type.
     * @return The data validated and stored in the provided reference.
     */

    public static <T> T scanv(T variable, String type){
        while (true){
            printlnf(YELLOW, "» ");
            try {
                String input = scanner.nextLine();
                switch (type.toLowerCase()) {
                    case "int":
                        variable = (T) Integer.valueOf(input);
                        break;
                    case "double":
                        variable = (T) Double.valueOf(input);
                        break;
                    case "string":
                        if (!input.isEmpty()) {
                            variable = (T) input;
                        } else {
                            throw new IllegalArgumentException("The input cannot be empty.");
                        }
                        break;
                    case "boolean":
                        variable = (T) Boolean.valueOf(input);
                        break;
                    default:
                        printlnf(RED, "Error: Unsupported data type.");
                        return null;
                }
                return variable;
            } catch (Exception e){
                printlnf(RED, "Invalid input. Make sure you enter a valid " + type + ".");
            }
        }
    }

    /**
     * @brief Pauses program execution until the user presses ENTER.
     * @details Displays a message on the console and waits for the user to press ENTER.
     */
    public static void pause_terminal() {
        Scanner input = new Scanner(System.in);
        printlnf(WHITE, "Press [ENTER] to continue...");
        input.nextLine();
    }

    /**
     * @brief Clean the console screen.
     * @details Determine the operating system and run the corresponding command to clear the screen.
     */
    public static void clear_screen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            printlnf(RED, "Cannot clean console");
        }
    }
}