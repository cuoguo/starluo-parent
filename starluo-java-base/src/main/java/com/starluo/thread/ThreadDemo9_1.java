package com.starluo.thread;

/**
 * @author cong.zhang
 * @date 2017/12/18.
 * @time 20:17.
 */
public class ThreadDemo9_1 {

    public static void main(String[] args) {
        new TestThread().start();
        TestRunnable t = new TestRunnable();
        new Thread(t).start();
        for (int i = 0; i < 1000 ; i++) {
            System.out.println("main 方法中的线程正在运行");
        }
    }
}

class TestThread extends Thread {

    @Override
    public void run(){
        for (int i = 0; i < 1000 ; i++) {
            System.out.println("TestThread 在运行");
        }
    }

}

class TestRunnable implements Runnable {

    @Override
    public void run(){
        for (int i = 0; i < 1000 ; i++) {
            System.out.println("TestRunnable 在运行");
        }
    }

}