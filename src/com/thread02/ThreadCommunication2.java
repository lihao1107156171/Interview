package com.thread02;

import java.util.concurrent.Semaphore;

/**
 * 	����һ���ź����������ڲ�ά���˶���߳�����������������̣߳��ͷŶ���̣߳�
 *	�̵߳��������ͷ���ͨ�� permit ������ʵ�ֵ�
 *	�߳�ͨ�� semaphore.acquire()������ȡ permit�������ǰ semaphore �� permit ���������̣߳�
 *	���û�����������߳�ֱ�� semaphore
 *	���� release���������ͷ� permit��
 *	���캯���в����� permit������ ����
 *
 */
public class ThreadCommunication2 {
	private static int num;//����һ��������Ϊ����
	
	// ����һ�� Semaphore��������������֤�ͷǹ�ƽ��ƽ����
	// �����������߳�A���к��ٸ������֤
	private static Semaphore semaphore = new Semaphore(0);
	
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//ģ���ʱ����֮���ʼ������ num
				try {
					Thread.sleep(1000);
					num = 1;
					// �ͷŸ������������֤�����䷵�ص��ź���
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
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ�� "+num);
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
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ�� "+num);
			}
		});
		
		//ͬʱ���� 3 ���߳�
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
