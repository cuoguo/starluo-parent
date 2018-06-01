package com.starluo.thread;

public class Run {
    public static void main(String[] args) {
//		MyThread a = new MyThread("A");
//		MyThread b = new MyThread("B");
//		MyThread c = new MyThread("C");
//		a.start();
//		b.start();
//		c.start();

		System.out.println("------------------------------------");
		// 共享数据
		MyThread mythread = new MyThread();
		//下列线程都是通过mythread对象创建的
		Thread aa=new Thread(mythread,"AA");
		Thread bb=new Thread(mythread,"BB");
		Thread cc=new Thread(mythread,"CC");
		Thread dd=new Thread(mythread,"DD");
		Thread ee=new Thread(mythread,"EE");
		aa.start();
		bb.start();
		cc.start();
		dd.start();
		ee.start();
	}
}