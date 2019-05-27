package com.qiangxi.demo;

import org.junit.Test;

import java.io.File;

/**
 * Example local unit test1, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FileTest {

    private String src = "/Users/renqiangqiang/Desktop/其他";
    private String dst = "/Users/renqiangqiang/Desktop/dad";

    @Test
    public void test() {
        File srcDir = new File(src);
        File dstDir = new File(dst);
        srcDir.renameTo(dstDir);
    }

}