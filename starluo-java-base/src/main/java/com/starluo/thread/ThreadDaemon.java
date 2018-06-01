package com.starluo.thread;

/**
 * 后台线程
 *
 * @author cong.zhang
 * @date 2017/12/19.
 * @time 14:36.
 */
public class ThreadDaemon {

    public static void main(String[] args) {
        com.starluo.thread.ThreadTest3 t = new com.starluo.thread.ThreadTest3();
        Thread tt = new Thread(t) ;
        tt.setDaemon(true);     // 设置后台运行
        tt.start();
    }

}