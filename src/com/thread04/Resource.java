package com.thread04;

public class Resource {
	private String name;
	private int count=1;
	private boolean flag=false;
	public synchronized void set(String name){
		while(flag) {
			try{
				//�̵߳ȴ���������������Դ
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.name=name+"---"+count++;
		System.out.println(Thread.currentThread().getName()+"...������..."+this.name);
		flag=true;
		//���ѵȴ��е�������
		this.notifyAll();
	}
	
	public synchronized void out(){
		 //������Դ
		while(!flag) {
		//�̵߳ȴ���������������Դ
			try{
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"...������..."+this.name);
		flag=false;
		//���������ߣ�������Դ
		this.notifyAll();
	}
	
	
}
