import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean onWord = false;
        int wordCount = 0;
        int value = reader.read();

        while (value != -1) {

            if (!Character.isWhitespace((char) value) && !onWord) {
                onWord = true;
                wordCount++;
            } else if (Character.isWhitespace((char) value)) {
                onWord = false;
            }
            value = reader.read();
        }
        reader.close();
        System.out.println(wordCount);
    }
}