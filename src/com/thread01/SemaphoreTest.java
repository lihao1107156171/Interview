package com.thread01;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 	Semaphore �����ź������������ƿ��Է�����Դ���߳�����
 * 	
 *
 */
public class SemaphoreTest {

	static class Student implements Runnable{
		
		private int num;
		private Playground playground;
		public Student(int num, Playground playground) {
			this.num = num;
            this.playground = playground;
		}
		
		@Override
		public void run() {
			try {
				Playground.Track track = playground.getTrack();
				if(track != null) {
					System.out.println("ѧ��" + num + "��" + track.toString() + "���ܲ�"); 
					TimeUnit.SECONDS.sleep(2);  // TimeUnitָ��ʱ�����ȵ�λ�ϳ���ʱ�䣬sleep��ʾ˯�߲���
					System.out.println("ѧ��" + num + "�ͷ�" + track.toString()); //�ͷ��ܵ� 
					playground.releaseTrack(track);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Executor executor = Executors.newCachedThreadPool(); 
		Playground playground = new Playground(); 
		for (int i = 0; i < 100; i++) { 
			executor.execute(new Student(i+1,playground)); 
		}
	}
	
}
