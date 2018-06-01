package com.starluo.jsoup;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * TODO 在www.java1234.com.httpclientDemo.HelloWorld2我们获取推酷网热门文章
 * 我们这里通过模拟浏览器的方法来重新获取
 * 
 * @date 2017年12月16日
 */
public class GetTuicoolHotArticles {

	public static void main(String[] args) throws ClientProtocolException, IOException {

        // TODO Auto-generated method stub
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
		HttpGet httpGet = new HttpGet("https://www.tuicool.com/"); // 创建httpget实例
		// 设置请求头信息Uer-Agent模拟浏览器
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");

		CloseableHttpResponse response = httpClient.execute(httpGet); // 执行httpget请求

		// 获取响应状态
		//System.out.println("响应状态为：" + response.getStatusLine());
		HttpEntity entity = response.getEntity(); // 获取返回实体
		 System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8"));
		// 获取响应内容类型Content-Type
		// System.out.println("content-type:" + entity.getContentType());
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭

	}

}