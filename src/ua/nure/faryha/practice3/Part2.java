package ua.nure.faryha.practice3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

public class Part2 {
    //Part2 - working with text and finding min and max word (Cyrillic included)

    public static final String p22 = "(?m)(?U)(\\w+)(?=\\b)";
    public static final String p23 = "(?U)([а-яёЁА-Яa-zA-z]+)";

    public static void main(String[] args) {
        String input = Util.readFile("part2.txt");
        System.out.println(Part2.convert(input));
    }

    public static String convert(String input) {
        String s = input;
        s = s.replaceAll("[^a-zA-Zа-яА-Я]", " ");
        String str[] = s.split("\\s+");
        //   System.out.println(s);
        int min = 100000;


        //1. finding minimal word-length among all words
        for (String i : str) {
            if (i.length() > 0 && i.length() < min)
                min = i.length();
        }
        //   System.out.println("min = " + min);


        //2. finding maximum word-length among all words
        Pattern p = Pattern.compile(p23);
        Matcher m = p.matcher(s);
        int max = 0;
        while (m.find()) {
            if (m.group().length() >= max) {
                max = m.group().length();
            }
        }
        m.reset();
        //    System.out.println("max = " + max);


        //3. Here the minimum-line words
        Pattern p2 = Pattern.compile(p22);
        Matcher m2 = p.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (m2.find()) {
            list.add(m2.group(1));
        }


        //4. Here we remove all word-duplicates
        int duplicates = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    // i & j refer to same entry so do nothing
                } else if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                    duplicates++;
                }
            }
        }
        //   System.out.println(list);
        //------------------------------------


        //3. continue to work with minimum-line words
        m2.reset();
        for (String ss : list) {
            if (ss.length() == min) {
                sb.append(ss).append(", ");
            }
        }
        sb.append(System.lineSeparator());
        //    System.out.print("Min: " + sb.toString());


        //5. Do the same with max words
        StringBuilder sb2 = new StringBuilder();
        for (String ss2 : list) {
            if (ss2.length() == max) {
                sb2.append(ss2).append(", ");
            }
        }
        sb2.append(System.lineSeparator());
        //    System.out.println("Max: " + sb2.toString());


        return "Min: " + sb.toString().replaceAll("\\,\\s$", "") + "Max: " + sb2.toString().replaceAll("\\,\\s$", "");
    }
}
