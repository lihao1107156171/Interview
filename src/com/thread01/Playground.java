package com.thread01;

import java.util.concurrent.Semaphore;

public class Playground {

	static class Track {
		private int num;
		
		public Track(int num) {
			this.num = num;
		}
		
		public String toString() {
            return "Track{" +
                    "num=" + num +
                    '}';
        }
	}
	
	private Track[] tracks = {new Track(1),new Track(2),new Track(3)};
	
	//volatile ����ǿ���̷߳�������
	private volatile boolean[] used = new boolean[3];
	
	// ��һ���������֤�������ڶ��������Ƿ��ǹ�ƽģʽ
	// ��ƽģʽ�µ���acquire��˳����ǻ�ȡ���֤��˳��
	// ���ǹ�ƽģʽ����ռʽ�ģ�Ҳ�����п���һ���µĻ�ȡ�߳�ǡ����һ�����֤�ͷ�ʱ�õ���������֤
	public Semaphore semphore = new Semaphore(3, true);
	
	// ��ȡ�ܵ�
	public Track getTrack() throws InterruptedException {
		semphore.acquire(); // ��ȡ���֤����ֱֹ�� �����С�����
		return getNextAvailableTrack();
	}
	
	// ����һ���ܵ�
	public void releaseTrack(Track track) {
		if(makeAsUsed(track))
			semphore.release();  // �ͷ����֤�����ظ��ź���
	}
	
	
	/**
	 * ����һ���ܵ�
	 * @param track
	 * @return
	 */
	private boolean makeAsUsed(Track track) {
		for (int i = 0; i < used.length; i++) {
			if (tracks[i] == track) { 
				if (used[i]) { 
					used[i] = false; 
					return true; 
				} else {
					return false; 
				} 
			} 
		}
		return false;
	}

	/**
	 *   ����û��ʹ�õ��ܵ�
	 * @return
	 */
	private Track getNextAvailableTrack() {
		for(int i=0;i<used.length;i++) {
			if(!used[i]) {
				used[1] = true;
				return tracks[i];
			}
		}
		return null;
	}
	
}
