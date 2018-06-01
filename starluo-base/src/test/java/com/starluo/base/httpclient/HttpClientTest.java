package com.starluo.base.httpclient;

import junit.framework.TestCase;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * commons-httpclient 包下
 *
 * @author cong.zhang
 * @date 2017/12/13.
 * @time 16:28.
 */
public class HttpClientTest extends TestCase {

    public void testSimpleHttpclient() throws IOException {
        String uriOrigin = "https://api.weibo.cn/2/comments/build_comments?flow=1&gsid=_2A253aQaXDeRxGeRG7VMY-CrJzjiIHXVSPx1frDV6PUJbi9ANLVL8kWpNUh1XyCIqBoUOdffNQ7TQ8PKwg3-HdjbC&wm=3333_2001&i=2931c64&b=0&from=1081293010&c=iphone&networktype=wifi&v_p=58&skin=default&v_f=1&s=eb2b4aea&lang=zh_CN&sflag=1&ua=iPhone7,1__weibo__8.1.2__iphone__os11.2.5&ft=0&aid=01Amt8nYaExnhsRYavzU6T61hgVBlxiRP2V5hbChShB6DSk_E.&id=4204922318757937&mid=4204922318757937&trim_level=1&is_show_bulletin=2&count=20&luicode=10000495&fetch_level=0&featurecode=10000001&uicode=10000002&_status_id=4204922318757937&is_reload=1&is_mix=1&rid=0_0_0_3071595631910201832&fromlog=102803&page=0&lfid=102803&moduleID=feed&since_id=0";
        String uriOneComment = "https://weibo.com/aj/v6/comment/big?ajwvr=6&id=4205087972445666&from=singleWeiBo&__rnd=" + System.currentTimeMillis();
        String uriTwoComment = "https://weibo.com/aj/v6/comment/big?ajwvr=6&id=4201437087423456&root_comment_max_id=205895240259114&root_comment_max_id_type=0&root_comment_ext_param=&page=2&filter=hot&sum_comment_number=2849&filter_tips_before=0&from=singleWeiBo&__rnd=1518000429736";
        //
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        // client.getHostConfiguration().setProxy("101.81.105.218", 80);

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
//        HttpMethod method = new GetMethod(uriOrigin);
        HttpMethod method = new GetMethod(uriOneComment);
//        HttpMethod method = new GetMethod(uriTwoComment);
        // 执行Get方法
        // client.executeMethod(method);

        // 获取状态行
        StatusLine statusLine = method.getStatusLine();
        if (statusLine != null && statusLine.getStatusCode() == HttpStatus.SC_OK) {
            // 字节流获取内容
            String content1 = new String(method.getResponseBody(), "utf-8");
            // 字符流流获取内容
            String content2 = IOUtils.toString(method.getResponseBodyAsStream(), "utf-8");

            // 字符串转成输入流
            InputStream inputStream = new ByteArrayInputStream(method.getResponseBodyAsString().getBytes("ISO-8859-1"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = "";
            String content3 = "";
            while((line = reader.readLine()) != null){
                content3 += line + "\n";
            }

            // 获取字节通过IOUtis转码
            String content4 = IOUtils.toString(method.getResponseBodyAsString().getBytes("ISO-8859-1"), Charset.defaultCharset().name());

            // 印服务器返回的状态
            System.out.println("状态码：" + statusLine.getStatusCode());
            // 打印返回的信息
            System.out.println("返回内容1：" + content1);
            System.out.println("返回内容2：" + content2);
            System.out.println("返回内容3：" + content3);
            System.out.println("返回内容4：" + content4);
            System.out.println("-----------------------------------------------");
        }

        // 使用POST方法
        //        HttpMethod method = new GetMethod(uriOrigin);
        method = new GetMethod(uriOneComment);
//        HttpMethod method = new GetMethod(uriTwoComment);

        // 执行POST方法
        client.executeMethod(method);

        // 获取状态行
        statusLine = method.getStatusLine();
        String content = IOUtils.toString(method.getResponseBody(), "utf-8");
        // 印服务器返回的状态
        System.out.println("状态码：" + statusLine.getStatusCode());
        System.out.println("返回内容：" + content);
        // 释放连接
        method.releaseConnection();
    }

}
