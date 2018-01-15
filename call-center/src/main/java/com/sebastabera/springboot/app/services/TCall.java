package com.sebastabera.springboot.app.services;

import com.sebastabera.springboot.app.models.entity.Call;

public class TCall implements Runnable{

	private Call call;
	
	private int time;
	
	public TCall(Call call) {
		this.call = call;
	}
	
	@Override
	public void run() {
		System.out.println("llamada en proceso " + call.getId() + " atendida por: " + call.getEmployee().getName());
		try {
			Thread.sleep(time * 1000);
			System.out.println("llamada " + call.getId() + " atendida por: " + call.getEmployee().getName() + " ha sido terminada");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
