package ua.nure.faryha.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static final String p51 = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public static final String p52 = "C|XC|L|XL|X|IX|V|IV|I";

    public static String decimal2Roman(int x) {
        String str[] = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int nums[] = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100};
        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            while (x >= nums[i]) {
                sb.append(str[i]);
                x -= nums[i];
            }
        }
        return sb.toString();
    }

    public static int roman2Decimal(String s) {
        if (s == null || s.isEmpty() || !s.matches(p51))
            return -1;
        Pattern p = Pattern.compile(p52);
        Matcher m = p.matcher(s);
        int[] mass = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String str[] = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int num = 0;
        while (m.find())
            for (int i = 0; i < str.length; i++)
                if (str[i].equals(m.group(0)))
                    num += mass[i];
        return num;
    }


    public static void main(String[] args) { //part5 - from decimal to Roman and back
        final int xx = 98;
        String ss = decimal2Roman(xx);
        System.out.println("");
        System.out.println(xx + " ====> " + decimal2Roman(xx) + " ====> " + roman2Decimal(ss));

        //DECIMAL ==decimal2Roman==> ROMAN ==roman2Decimal==> DECIMAL


    }
}
