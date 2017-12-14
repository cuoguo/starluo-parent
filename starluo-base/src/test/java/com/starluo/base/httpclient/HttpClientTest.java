package com.starluo.base.httpclient;

import junit.framework.TestCase;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

/**
 * commons-httpclient 包下
 *
 * @author cong.zhang
 * @date 2017/12/13.
 * @time 16:28.
 */
public class HttpClientTest extends TestCase {

    public void testSimpleHttpclient() throws IOException {
        String uri = "http://www.baidu.com";
        //
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        // client.getHostConfiguration().setProxy("101.81.105.218", 80);

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
        HttpMethod method = new GetMethod(uri);
        // 执行Get方法
        client.executeMethod(method);

        // 获取状态行
        StatusLine statusLine = method.getStatusLine();

        // 字节流获取内容
        String content1 = new String(method.getResponseBody(), "utf-8");
        // 字符流流获取内容
        String content2 = IOUtils.toString(method.getResponseBodyAsStream(), "utf-8");
        // 字符串

        InputStream inputStream = new ByteArrayInputStream(method.getResponseBodyAsString().getBytes("ISO-8859-1"));
        InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        try {
            while ((count = reader.read(buf)) != -1) {
                buffer.append(buf, 0, count);
            }
        } finally {
            reader.close();
        }
        String content3 = buffer.toString();
        String content4 = IOUtils.toString(method.getResponseBodyAsString().getBytes("ISO-8859-1"), Charset.defaultCharset().name());

        // 印服务器返回的状态
        System.out.println("状态码：" + statusLine.getStatusCode());
        // 打印返回的信息
        System.out.println("返回内容1：" + content1);
        System.out.println("返回内容2：" + content2);
        System.out.println("返回内容3：" + content3);
        System.out.println("返回内容3：" + content4);
        System.out.println("-----------------------------------------------");
        // 使用POST方法
        method = new PostMethod(uri);
        // 执行POST方法
        client.executeMethod(method);

        // 获取状态行
        statusLine = method.getStatusLine();
        String content = new String(method.getResponseBody(), "utf-8");
        // 印服务器返回的状态
//        System.out.println("状态码：" + statusLine.getStatusCode());
        // 打印返回的信息
//        System.out.println("返回内容：" + content);
        // 释放连接
        method.releaseConnection();
    }

}
