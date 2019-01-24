package ua.nure.faryha.practice3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

public class Part1 { // Part1 - working with Login/Name/Email
    public static final String P1 = "(?m)(?U)^(.+);(.+);(.+@+.+)$";
    public static final String P2 = "(?m)(?U)^(\\w+);(\\w+\\s)(\\w+);(\\w+@+.+)$";
    public static final String P3 = "(?m)(?U)^(.+);(.+);(.+@+)(.*)$";
    public static final String P4 = "(?m)(?U)^(.+);(.+);(.+)$";
    public static final String P41 = "(?m)(?U)^(.*);(.+);(.+@.+)(?=\\b)$";

    public static void main(String[] args) {
        String input = Util.readFile("part1.txt");
        System.out.println(Part1.convert1(input));
        System.out.println(Part1.convert2(input));
        System.out.println(Part1.convert3(input));
        System.out.println(Part1.convert4(input));
    }

    // ivanov ==> ivanov@mail.ru
    private static String convert1(String input) {
        return input.replaceAll(P1, "$1 ==> $3").replaceFirst(".+", "");
    }

    // Ivanov Ivan (email: ivanov@mail.ru)
    private static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(P2);
        Matcher m = p.matcher(input);
        while (m.find()) {
            sb.append(m.group(3)).append(" ").append(m.group(2)).append("(email: ").append(m.group(4)).append(")").append(System.lineSeparator());
        }
        return sb.toString();
    }

    /* mail.ru ==> ivanov, bush
    google.com ==> петров, obama */
    private static String convert3(String input) {
        //    System.out.println();
        Pattern p = Pattern.compile(P3);
        Matcher m = p.matcher(input);
        ArrayList<String> listOfDomens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            if (!listOfDomens.contains(m.group(4))) listOfDomens.add(m.group(4));
        }
        m.reset();
        for (String s : listOfDomens) {
            sb.append(s).append(" ==> ");
            while (m.find()) {
                if (m.group(4).equals(s)) {
                    sb.append(m.group(1)).append(", ");
                }
            }
            sb.append('\n');
            m.reset();
        }
        return sb.toString().replaceAll(", \n", "\n");
    }

    /*
    Login;Name;Email;Password
    ivanov;Ivan Ivanov;ivanov@mail.ru;1707
     */
    private static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(P41);
        Matcher m = p.matcher(input);
        while (m.find()) {
            sb.append(m.group(1)).append(";").append(m.group(2)).append(";").append(m.group(3)).append(";").append((int) (Math.random() * 8999) + 1000).append(System.lineSeparator());
        }
        final String s11 = "Login;Name;Email;Password";
        return input.replaceFirst(P4, s11).replaceAll(P41, "").replaceAll("$eol", "").replaceAll("(?ms)^\\s*$[\r?\n]{1,}", "") + sb.toString();
    }
}

