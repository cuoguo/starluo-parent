package com.starluo.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 */
public class Demo01 {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/"); // 创建httpget实例
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ");

        CloseableHttpResponse response = httpclient.execute(httpGet);   // 执行get请求
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        // System.out.println("网页内容：" + content); // 指定编码打印网页内容
        response.close(); // 关闭流和释放系统资源

        Document doc = Jsoup.parse(content);
        Element element = doc.getElementById("list_article");
        Elements list_article_item = element.getElementsByClass("list_article_item");
        for (Element article_item : list_article_item) {
            String src_link = article_item.select(".article_thumb_image").first().getElementsByTag("img").attr("src");
            String title = article_item.getElementsByClass("aricle_item_info").get(0).getElementsByClass("title").get(0).getElementsByTag("a").text();
            Elements span_list = article_item.getElementsByClass("tip").get(0).getElementsByTag("span");
            System.out.println(" title:" + title + "\n link:" + src_link + "\n span:" + span_list.get(0).text() + span_list.get(1).text() + span_list.get(2).text());
            System.out.println("-----------------------------------------------");
        }
    }

}