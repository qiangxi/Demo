package com.qiangxi.demo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By renqiangqiang . 2019-07-17
 */
public class RegexTest {

    @Test
    public void demo1() {
        String str = "[03:23.23] 我是一个可阿达(03:23.23) ";
        String regex = "\\[(\\d{2}:\\d{2}\\.\\d{2})]\\W*?\\((\\d{2}:\\d{2}\\.\\d{2})\\)";
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);
    }

    @Test
    public void demo2() {
        String str = "188 9400 1263";
        String regex = "\\d{3}\\s\\d{4}\\s\\d{4}";
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);
    }

    @Test
    public void demo3() {
        String str = "<img src = 'https://www.baidu.com/img/ddadada.png' color = '#3e3e3e'/>";
        String regex = "<img\\s*src\\s*=\\s*'(.*)'\\s*color.*/>";
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);
    }

    @Test
    public void demo4() {
        String str = "<img src = 'https://www.baidu.com/img/ddadada.png' color = '#3e3e3e'/>";
        String regex = "<img\\s*src\\s*=\\s*'(?<url>.*)'\\s*color.*/>";
        System.out.println(str.replaceAll(regex, "我"));
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        boolean found = matcher.find();
        System.out.println("found = " + found);
        if (found) {
            final int count = matcher.groupCount();
            System.out.println("count = " + count);
            System.out.println("url = " + matcher.group("url"));
            if (count == 0) {
                System.out.println("count = 0 result = " + matcher.group());
            } else {
                for (int index = 0; index <= count; index++) {
                    System.out.println("result = " + matcher.group(index));
                }
            }
        }
    }

    @Test
    public void demo5() {
        String str = "We are human_in earth.";
        // String regex = "human.*in";
        String regex = "h\\w*?n";  // 非贪婪模式，尽可能少的匹配字符,如果 ? 是限定符 * 或 + 或 ? 或 {} 后面的第一个字符，那么表示非贪婪模式
        // String regex = "h\\w*n";  // 贪婪模式，默认是贪婪模式，尽可能多的匹配字符
        // String regex = "\\s";
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);

    }

    @Test
    public void demo6() {
        String Str = "Hello , World .";
        String pattern = "(\\w)(\\s+)([.,])";
        // $0 匹配 `(\w)(\s+)([.,])` 结果为 `o空格,` 和 `d空格.`
        // $1 匹配 `(\w)` 结果为 `o` 和 `d`
        // $2 匹配 `(\s+)` 结果为 `空格` 和 `空格`
        // $3 匹配 `([.,])` 结果为 `,` 和 `.`
        String s = "a*b";
        System.out.println(Str.replaceAll(pattern, "$1$3")); // Hello, World.
        process(pattern, Str);
    }

    @Test
    public void demo7() {
        String str = "aabcaadfaacbadsdbgg";
        // String regex = "a*b";// 匹配多个a且后面跟一个b
        String regex = "a(?!b)"; //匹配a后面不是b
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);
    }

    @Test
    public void demo8() {
        String str = "AbCdEfG";
        // String regex = "[A-Z]"; //匹配大写字符
        // String regex = "[a-z]"; //匹配大=小写字符
        String regex = "(?i)[a-z]"; //匹配a-z，忽略大小写
        System.out.println(str.replaceAll(regex, "我"));
        process(regex, str);
    }

    @Test
    public void demo9() {
        String str = "\\";
        String regex = "\\\\";
        Pattern compile = Pattern.compile(regex);
        // System.out.println(compile.pattern());
        // System.out.println(str.replaceAll(regex, "我"));
        process("\\d+", "2223aa");
    }


    private void process(String regex, String content) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(content);
        boolean found = matcher.find();
        System.out.println("found = " + found);
        if (found) {
            final int count = matcher.groupCount();
            System.out.println("count = " + count);
            if (count == 0) {
                System.out.println("count = 0 result = " + matcher.group());
            } else {
                for (int index = 0; index <= count; index++) {
                    System.out.println("result = " + matcher.group(index));
                }
            }
        }
    }
}
