public class PrintLeapYear {
    public static void main(String[] args) {
        //A leap year is divisible by 4
        //Century year is a year divisible by 400
        int year = 1990;
        boolean leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }
        if (leap) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + "is not a leap year");
        }

    }
}