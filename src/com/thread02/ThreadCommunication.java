package com.thread02;

public class ThreadCommunication {
	private static int num;//����һ��������Ϊ����
	
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//ģ���ʱ����֮���ʼ������ num
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
				 System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ�� "+num);
			}
		});
			
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ�� "+num);
			}
		});
		
		//ͬʱ���� 3 ���߳�
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
