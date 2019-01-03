package com.thread04;

public class ThreadTest {

	public static void main(String[] args) {
		Resource r=new Resource();
		Producer pro=new Producer(r);
		Consumer con=new Consumer(r);
		Thread t1=new Thread(pro);
		Thread t2=new Thread(con);
		t1.start();
		t2.start();
	}

}
