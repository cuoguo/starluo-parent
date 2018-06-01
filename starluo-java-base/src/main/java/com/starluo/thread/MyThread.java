package com.starluo.thread;

public class MyThread extends Thread {

	private int count = 5;

	public MyThread() {
	}

	public MyThread(String name) {
        super();
		this.setName(name);
	}

	@Override
	public synchronized void run() {
		super.run();
		while (count > 0) {
			count--;
			System.out.println("由 " + MyThread.currentThread().getName() + " 计算，count=" + count);
		}
	}
}