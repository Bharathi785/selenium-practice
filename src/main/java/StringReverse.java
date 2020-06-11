public class StringReverse {
    public static void main(String[] args) {
        //Method 1
        String inputString = "bharathibalaji";
        char[] chars = inputString.toCharArray();
        int length = chars.length;
        System.out.println(length);

        for (int i = length - 1; i >= 0; i--) {
            System.out.println(chars[i]);
        }
        //Method 2 
        StringBuffer stringBuffer = new StringBuffer(inputString);
        System.out.println(stringBuffer.reverse());

        //Method 3
        StringBuilder stringBuilder = new StringBuilder(inputString);
        System.out.println(stringBuilder.reverse());

        //Method 4
        String rev = "";
        for (int i = length - 1; i >= 0; i--) {
            rev = rev + inputString.charAt(i);
            System.out.println(rev);
        }
    }


}
