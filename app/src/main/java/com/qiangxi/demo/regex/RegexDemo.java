package com.qiangxi.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By renqiangqiang . 2019-07-17
 */
public class RegexDemo {

    public static void main(String[] args) {
        // String str = "d(cacac ara";
        String str = "[03:23.23] 我是一个可阿达(03:23.23) ";
        // String regex = "[a][r]";
        String regex = "\\[(\\d{2}:\\d{2}\\.\\d{2})]\\W*?\\((\\d{2}:\\d{2}\\.\\d{2})\\)";
        System.out.println(str.replaceAll(regex, "我"));
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        boolean found = matcher.find();
        System.out.println("found = " + found);
        if (found) {
            final int count = matcher.groupCount();
            System.out.println("count = " + count);
            if (count == 0) {
                System.out.println("result = " + matcher.group());
            } else {
                for (int index = 0; index <= count; index++) {
                    System.out.println("result = " + matcher.group(index));
                }
            }
        }
    }

}
