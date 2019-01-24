package ua.nure.faryha.practice3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Part3 {
    //part3 - working with first letters of every word - making them capital (Cyrillic included)
    public static final String p32 = "(?m)(?U)(\\b\\w)(\\S\\S)";

    public static void main(String[] args) {
        String input = Util.readFile("part3.txt");
        System.out.println(Part3.convert(input));
    }

    public static String convert(String input) {
        String s = input;
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(p32);
        Matcher m = p.matcher(s);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase() + m.group(2));
        }
        return m.appendTail(sb).toString();


    }

}
