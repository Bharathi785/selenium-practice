import java.util.Scanner;

public class PrintInteger {
    /**
     * Java program to print an Innteger enteted by the user
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a number");
        int number = reader.nextInt();
        System.out.println("you entered:" + number);

    }
}
