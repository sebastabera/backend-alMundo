package com.sebastabera.springboot.app.services;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.sebastabera.springboot.app.models.entity.Call;

public class TCall implements Runnable{

	private Call call;
	
	private int time;
	
	private CountDownLatch  cdl;
	
	public TCall(Call call, int time, CountDownLatch cdl) {
		this.call = call;
		this.time = time;
		this.cdl = cdl;
	}
	
	@Override
	public synchronized void run() {
		System.out.println("llamada en proceso " + call.getId() + " atendida por: " + call.getEmployee().getName() + " tiempo: " + this.time);
	
		int cont = 0;
		while(cont <= time) {
			try {
				TimeUnit.SECONDS.sleep(1);
				cont+= 1;
				System.out.println("llamada " + call.getId() + " atendida por: " + call.getEmployee().getName() + " esta en proceso");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("llamada " + call.getId() + " atendida por: " + call.getEmployee().getName() + " fue terminada");
		cdl.countDown();
		
	}

}
