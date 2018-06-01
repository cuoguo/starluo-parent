package com.starluo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * chapter 02
 *
 * @author cong.zhang
 * @date 2018/4/3.
 * @time 12:03.
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.tuicool.com");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println("状态行：" + response.getStatusLine());
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");  // 获取网页内容
        //System.out.println("获取网页：" + content);
        System.out.println("Content-Type：" + entity.getContentType().getValue());
        response.close();
        httpClient.close();
    }

}
