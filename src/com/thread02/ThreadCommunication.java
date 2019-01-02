package com.thread02;

public class ThreadCommunication {
	private static int num;//定义一个变量作为数据
	
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//模拟耗时操作之后初始化变量 num
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				num = 1;
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				 System.out.println(Thread.currentThread().getName()+"获取到 num 的值为： "+num);
			}
		});
			
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为： "+num);
			}
		});
		
		//同时开启 3 个线程
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
