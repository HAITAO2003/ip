package utils;

import java.util.Scanner;
import java.util.Optional;

public class Utils {

    public static String get_input(Optional<String> leading_text) {
        Scanner input = new Scanner(System.in);

        String prompt = leading_text.orElse("");
        System.out.print(prompt);

        String ret = input.next();
        System.out.println("_".repeat(60));
        return ret;
    }
    public static void output(String output_str)
    {
        System.out.println(output_str);
        System.out.println("_".repeat(60));
    }
}
