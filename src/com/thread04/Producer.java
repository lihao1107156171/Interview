package com.thread04;

/**
 *  生产者
 *
 */
public class Producer implements Runnable{

	private Resource res;
	Producer(Resource res){
		this.res=res;
	}
	
	@Override
	public void run() {
		while(true){
			res.set("商品");
		 }
	}

}
