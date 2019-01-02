package com.thread03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	private int count = 0;
	private Lock lock = new ReentrantLock();//���� lock ��
	
	//���� 1
	public Runnable run1 = new Runnable(){
		public void run() {
			lock.lock(); //����
			while(count < 1000) {
				try {
					//��ӡ�Ƿ�ִ�и÷���
					System.out.println(Thread.currentThread().getName() + " run1: "+count++);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lock.unlock(); // �ͷ���
		}
	};
	
	//���� 2
	public Runnable run2 = new Runnable(){
		public void run() {
			lock.lock();
			while(count < 1000) {
				try {
					System.out.println(Thread.currentThread().getName() +
							" run2: "+count++);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
	};
	
	public static void main(String[] args) {
		LockTest t = new LockTest(); //����һ������
		new Thread(t.run1).start();//��ȡ�ö���ķ��� 1
		new Thread(t.run2).start();//��ȡ�ö���ķ��� 2
	}
}
