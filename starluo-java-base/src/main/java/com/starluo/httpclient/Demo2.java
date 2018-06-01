package com.starluo.httpclient;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * @author cong.zhang
 * @date 2018/4/3.
 * @time 14:00.
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.tuicool.com");

        // 代理设置
        HttpHost proxy = new HttpHost("218.3.164.133", 61202);
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000)   // 设置链接超时时间 10秒
                .setSocketTimeout(10000)   // 设置读取内容响应时间 10秒
                .setProxy(proxy)
                .build();
        httpGet.setConfig(config);

        //

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println("Content-Type：" + entity.getContentType().getValue());
        if (entity != null) {
            if (entity.getContentType().getValue().split("/")[0].equals("image")) {
                InputStream inputStream = entity.getContent();
                FileUtils.copyToFile(inputStream, new File("C:\\bbb.png"));
            }

            String content = EntityUtils.toString(entity, "utf-8");  // 获取网页内容
            System.out.println(content);
        }

        response.close();
        httpClient.close();
    }

}
