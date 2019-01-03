package com.thread04;

public class Resource {
	private String name;
	private int count=1;
	private boolean flag=false;
	public synchronized void set(String name){
		while(flag) {
			try{
				//线程等待。消费者消费资源
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.name=name+"---"+count++;
		System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
		flag=true;
		//唤醒等待中的消费者
		this.notifyAll();
	}
	
	public synchronized void out(){
		 //消费资源
		while(!flag) {
		//线程等待，生产者生产资源
			try{
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
		flag=false;
		//唤醒生产者，生产资源
		this.notifyAll();
	}
	
	
}
