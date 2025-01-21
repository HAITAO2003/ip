package utils;

import java.util.Scanner;
import java.util.Optional;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);  // Single scanner instance

    public static String get_input(Optional<String> leading_text) {

        String prompt = leading_text.orElse("");
        System.out.print(prompt);

        String ret = scanner.nextLine();
        System.out.println("_".repeat(60));
        return ret;
    }
    public static void output(String output_str) {
        if (output_str.equals("")) {
            System.out.println("_".repeat(60));
        }else{
            System.out.println(output_str);
            System.out.println("_".repeat(60));

        }
    }
}
