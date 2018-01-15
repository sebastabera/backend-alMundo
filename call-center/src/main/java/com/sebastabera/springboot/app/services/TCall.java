package com.sebastabera.springboot.app.services;

import com.sebastabera.springboot.app.models.entity.Call;

public class TCall implements Runnable{

	private Call call;
	
	private int time;
	
	public TCall(Call call, int time) {
		this.call = call;
		this.time = time * 1000;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			System.out.println("llamada en proceso " + call.getId() + " atendida por: " + call.getEmployee().getName() + " tiempo: " + this.time/1000);
			int cont = 0;
			while(cont <= time) {
				try {
					this.wait(1000);
					cont+= 1000;
					System.out.println("llamada " + call.getId() + " atendida por: " + call.getEmployee().getName() + " esta en proceso");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("llamada " + call.getId() + " atendida por: " + call.getEmployee().getName() + " fue terminada");
		}
		
	}

}
