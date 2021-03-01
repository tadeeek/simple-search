package displayResult;

public class DisplayResult {

    public static void display(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i].trim());
            if (i == strings.length - 1) {
            } else
                System.out.print(" ");
        }
    }
}
