package com.starluo.reptile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 第一个爬虫测试
 * Created by DuFei on 2017/7/27.
 */
public class FirstTest {

    static long total_number = 0;

    public static void main(String[] args) {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();


        for (int i=0;i<= total_number; i++) {
            contentHandle(httpClient);
        }

        System.out.println(total_number);
    }

    // 获取评论总数
    private static void contentHandle(CloseableHttpClient httpClient) {
        //使用HttpGet方式请求网址
        HttpGet httpGet = new HttpGet("https://api.weibo.cn/2/comments/build_comments?flow=1&networktype=wifi&is_mix=1&max_id=0&is_show_bulletin=2&uicode=10000002&moduleID=700&trim_user=0&is_reload=1&featurecode=10000085&wb_version=3562&is_encoded=0&c=android&i=261d164&s=05233ddd&ft=0&id=4187441533567311&ua=OPPO-OPPO%20R9%20Plusm%20A__weibo__8.1.2__android__android6.0.1&wm=2468_1001&aid=01Asmg9kRh_t5p0huqyaOB_fEB-zeFQsRsrIToBlHt4SmcOOc.&v_f=2&v_p=58&from=1081295010&gsid=_2A253f99TDeRxGeBN6FMX8SfPwjSIHXVSLVWbrDV6PUJbkdANLVLekWpNRGvvo25ZRTULjMa7u3dCu__LPCdZup2V&lang=zh_CN&lfid=2302835497993620&skin=default&count=100&oldwm=2468_90004&sflag=1&ignore_inturrpted_error=true&luicode=10000198&fetch_level=0&is_append_blogs=0&max_id_type=0");
        //获取网址的返回结果
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取返回结果中的实体
        HttpEntity entity = response.getEntity();

        //将返回的实体输出
        try {
            String contentStr = EntityUtils.toString(entity,"GBK");
            System.out.println(contentStr);
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            JSONArray commentsArray = jsonObject.getJSONArray("root_comments");
            total_number = jsonObject.getLong("total_number");
            if (commentsArray == null) {
                return;
            }
            for (int i = 0; i < commentsArray.size(); i++) {
                JSONObject cardObject = commentsArray.getJSONObject(i);
                String content = cardObject.getString("text");
                String created_at = cardObject.getString("created_at");
                JSONObject userObje = cardObject.getJSONObject("user");
                String name = userObje.getString("name");

                System.out.println(name + "," + content + "," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(created_at)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}