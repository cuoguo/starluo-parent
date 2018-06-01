package com.starluo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.IOException;


/**
 * @author cong.zhang
 * @date 2018/3/30.
 * @time 17:17.
 */
public class Demo {

    public static void main(String[] args) throws IOException {
//        testGet();
       // testPost();
        JOptionPane.showInputDialog(null, "填写名称","测试标题", JOptionPane.QUESTION_MESSAGE);
    }

    public static void testGet() throws IOException {
        Document doc = Jsoup.connect("https://www.baidu.com").get();
        String title = doc.title();
        System.out.println("get:" + title);
    }

    public static void testPost() throws IOException {
        Document doc = Jsoup.connect("https://api.mrcheckin.cn/checkinActivity/detail")
                .data("parameters", "{\"common\":{},\"data\":{\"id\":\"384\"}}")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5083.400 QQBrowser/10.0.972.400")
                //.cookie("auth", "token")
                .timeout(3000)
                .post();
        String title = doc.title();
        System.out.println("post:" + doc.select("body"));
    }


}
