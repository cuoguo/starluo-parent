package com.starluo.thread;

/**
 * @author cong.zhang
 * @date 2017/12/19.
 * @time 16:23.
 */
public class ThreadDemo9_5 {
    public static void main(String[] args) {
        TestThread5 t = new TestThread5();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}

class TestThread5 implements Runnable{

    int tickes = 20;
    @Override
    public void run() {
        while (true){
            sale();
        }
    }

    /**
     * 同步方法
     */
    public synchronized void sale(){
        if(tickes >0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"出票"+ tickes--);
        }
    }
}
