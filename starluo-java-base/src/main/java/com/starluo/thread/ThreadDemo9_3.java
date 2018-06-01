package com.starluo.thread;

/**
 * @author cong.zhang
 * @date 2017/12/19.
 * @time 13:59.
 */
public class ThreadDemo9_3 {

    public static void main(String[] args) {
        // 启动了四个线程，分别执行各自的操作
//        new TestThread1().start();
//        new TestThread1().start();
//        new TestThread1().start();
//        new TestThread1().start();

        // 启动了四个线程，并实现了资源共享的目的
        TestRunnable1 t = new TestRunnable1();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

}

class TestThread1 extends Thread {
    private int tickets=20;

    @Override
    public void run() {
       while (true){
           if(tickets >0){
               System.out.println(Thread.currentThread().getName()+"出售票"+tickets--);
           }
       }
    }
}

class TestRunnable1 implements Runnable {
    private int tickets=20;

    @Override
    public void run() {
        while (true){
            if(tickets >0){
                System.out.println(Thread.currentThread().getName()+"出售票"+tickets--);
            }
        }
    }
}