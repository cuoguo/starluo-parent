package com.starluo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cong.zhang
 * @date 2018/4/3.
 * @time 10:32.
 */
public class ExecutorTest {

    private static Integer page = 1;
    private static boolean exeflag = true;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while (exeflag) {
            if (page <= 100) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("爬取的第" + page + "个网页");
                        page++ ;
                    }
                });
            } else {
                if (((ThreadPoolExecutor)executorService).getActiveCount() == 0) {  // 活动线程个数为0
                    executorService.shutdown();
                    exeflag = false;
                    System.out.println("爬虫任务已经完成");
                }
            }

            try {
                Thread.sleep(1000);     // 线程休息一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
