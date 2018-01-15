package com.sebastabera.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastabera.springboot.app.dao.ICallDaoRepository;
import com.sebastabera.springboot.app.dao.IEmployeeDaoRepository;
import com.sebastabera.springboot.app.models.entity.Call;
import com.sebastabera.springboot.app.models.entity.Employee;

public class Dispatcher implements Runnable{

	@Autowired
	private IEmployeeDaoRepository employeeDaoRepository;
	
	@Autowired 
	private ICallDaoRepository callDaoRepository;
	
	private Call call;
	
	@Override
	public void run() {
		if(verifyMaxNumberCalls()) {
			if(dispatcherCall()) {
				System.out.println(call);
			} else {
				System.out.println("No hay empleados disponibles para atender la llamada");
			}
		} else {
			System.out.println("Hay mas de 10 llamadas en curso");
		}
	}
	
	public boolean dispatcherCall() {
		Employee employeeCall = employeeToCall();
		if(employeeCall != null) {
			call = new Call(employeeCall, true);
			callDaoRepository.save(call);
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean verifyMaxNumberCalls() {
		List<Call> calls = (List<Call>) callDaoRepository.findByStateCalls();
		return (calls.size() <=10) ? true : false;
	}
	
	public Employee employeeToCall() {		
		Employee operador = employeeDaoRepository.findByPosition("operador");
		Employee supervisor = employeeDaoRepository.findByPosition("supervisor");
		Employee director = employeeDaoRepository.findByPosition("director");
		return (operador != null) ? operador : (supervisor != null) ? supervisor : (director != null) ? director : null;
	}
	
}
