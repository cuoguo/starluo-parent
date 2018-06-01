package com.starluo.thread;

/**
 * @author cong.zhang
 * @date 2017/12/19.
 * @time 16:00.
 */
public class ThreadTest3 implements Runnable {

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + " is runing.");
        }
    }
}
