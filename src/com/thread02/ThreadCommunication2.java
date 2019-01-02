package com.thread02;

import java.util.concurrent.Semaphore;

/**
 * 	定义一个信号量，该类内部维持了多个线程锁，可以阻塞多个线程，释放多个线程，
 *	线程的阻塞和释放是通过 permit 概念来实现的
 *	线程通过 semaphore.acquire()方法获取 permit，如果当前 semaphore 有 permit 则分配给该线程，
 *	如果没有则阻塞该线程直到 semaphore
 *	调用 release（）方法释放 permit。
 *	构造函数中参数： permit（允许） 个数
 *
 */
public class ThreadCommunication2 {
	private static int num;//定义一个变量作为数据
	
	// 创建一个 Semaphore与给定数量的许可证和非公平公平设置
	// 这里设置在线程A运行后再给出许可证
	private static Semaphore semaphore = new Semaphore(0);
	
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//模拟耗时操作之后初始化变量 num
				try {
					Thread.sleep(1000);
					num = 1;
					// 释放给定数量的许可证，将其返回到信号量
					semaphore.release(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为： "+num);
			}
		});
			
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					semaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为： "+num);
			}
		});
		
		//同时开启 3 个线程
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
