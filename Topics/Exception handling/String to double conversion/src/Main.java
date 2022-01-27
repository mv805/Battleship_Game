import java.lang.Exception;

class Converter {

    /**
     * It returns a double value or 0 if an exception occurred
     */
    public static double convertStringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        }
        catch (IllegalArgumentException e){
            return 0;
        }
        catch (Exception e){
            return 0;
        }
    }
}