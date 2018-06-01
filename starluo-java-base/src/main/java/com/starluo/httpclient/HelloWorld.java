package com.starluo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author cong.zhang
 * @date 2018/4/3.
 * @time 11:43.
 */
public class HelloWorld {

    // 1. 创建httpclient实例
    static CloseableHttpClient httpClient;
    static HttpGet httpGet;
    static CloseableHttpResponse response;

    public static void main(String[] args) throws IOException {
        // 1. 创建httpclient实例
        httpClient = HttpClients.createDefault();

        // 2. 创建httpget实例
        httpGet = new HttpGet("http://www.tuicool.com");
        // 设置请求头信息Uer-Agent模拟浏览器
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");

        // 3. 执行http get请求

        response = httpClient.execute(httpGet);


        // 4. 获取返回实体
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");  // 获取网页内容
        System.out.println("获取网页：" + content);

        if (response != null) {
            response.close();
        }

        if (content == null) {
            httpClient.close();
        }
    }

}
