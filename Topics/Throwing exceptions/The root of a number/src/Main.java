import java.util.*;
import java.lang.IllegalArgumentException;

public class Main {

    public static double sqrt(double x) {
        try {
            return Math.sqrt(x);
        }catch (IllegalArgumentException e){
            System.out.print("Expected non-negative number, " +
                    "got " +  String.valueOf(x));
            return 0;
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        String value = new Scanner(System.in).nextLine();
        try {
            double res = sqrt(Double.parseDouble(value));
            System.out.println(res);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}