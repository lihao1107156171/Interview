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
	
	//volatile 属性强制线程访问主存
	private volatile boolean[] used = new boolean[3];
	
	// 第一个参数许可证数量，第二个参数是否是公平模式
	// 公平模式下调用acquire的顺序就是获取许可证的顺序
	// 而非公平模式是抢占式的，也就是有可能一个新的获取线程恰好在一个许可证释放时得到了这个许可证
	public Semaphore semphore = new Semaphore(3, true);
	
	// 获取跑道
	public Track getTrack() throws InterruptedException {
		semphore.acquire(); // 获取许可证，阻止直到 “所有”可用
		return getNextAvailableTrack();
	}
	
	// 返回一个跑到
	public void releaseTrack(Track track) {
		if(makeAsUsed(track))
			semphore.release();  // 释放许可证，返回给信号量
	}
	
	
	/**
	 * 返回一个跑道
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
	 *   查找没人使用的跑道
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
