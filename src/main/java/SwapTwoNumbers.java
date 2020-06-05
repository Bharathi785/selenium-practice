public class SwapTwoNumbersTempVariable {
    public static void main(String[] args) {

        int a = 20;
        int b = 30;
        System.out.println("before swapping");
        System.out.println(a);
        System.out.println(b);
        System.out.println("after swapping");
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.println("after swapping");
        System.out.println(a);
        System.out.println(b);

    }
}
