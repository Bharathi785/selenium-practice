public class LargestAmongThreeeNumbers {

    public static void main(String[] args) {
        int a = 400;
        int b = 700;
        int c = 5800;

        if (a > b && a > c) {
            System.out.println("A is greatest");
        } else if (b > c) {
            System.out.println("B is greatest");
        } else {
            System.out.println("C is greatest");
        }
    }
}
