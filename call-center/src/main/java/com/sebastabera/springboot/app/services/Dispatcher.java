package com.sebastabera.springboot.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastabera.springboot.app.dao.ICallDaoRepository;
import com.sebastabera.springboot.app.dao.IEmployeeDaoRepository;
import com.sebastabera.springboot.app.dao.IPositionDaoRepository;
import com.sebastabera.springboot.app.models.entity.Call;
import com.sebastabera.springboot.app.models.entity.Employee;
import com.sebastabera.springboot.app.models.entity.Position;

@Service
public class Dispatcher {

	@Autowired
	private IEmployeeDaoRepository employeeDaoRepository;
	
	@Autowired 
	private ICallDaoRepository callDaoRepository;
	
	private List<Call> calls;
	
	private Call call;
	
	public Dispatcher() {
		calls = new ArrayList<Call>();
	}
	
	public void createCall(CountDownLatch cdl) throws InterruptedException {
		if(attendCall()) {
			int numero = (int) (Math.random() * 5) + 5;
			TCall newCall = new TCall(call, numero, cdl);
			Thread llamada = new Thread(newCall);			
			llamada.start();
		} else {
			
		}
	}
	public boolean attendCall() {
		if(verifyMaxNumberCalls()) {
			if(dispatcherCall()) {
				System.out.println(call);
				return true;
			} else {
				System.out.println("No hay empleados disponibles para atender la llamada");
				return false;
			}
		} else {
			System.out.println("Hay mas de 10 llamadas en curso");
			return false;
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
		List<Call> calls = (List<Call>) callDaoRepository.findAll();
		return (calls != null && calls.size() <=10) ? true : false;
	}
	
	public void stopCall(Long id) {
		Call callAux = callDaoRepository.findOne(id);
		callAux.setState(false);
		callDaoRepository.save(callAux);
	}
	
	public Employee employeeToCall() {		
		List<Employee> operador = employeeDaoRepository.findByPosition("operador");
		List<Employee> supervisor = employeeDaoRepository.findByPosition("supervisor");
		List<Employee> director = employeeDaoRepository.findByPosition("director");
		return (operador.size() != 0) ? operador.get(0) : (supervisor.size() != 0) ? supervisor.get(0) : (director.size() != 0) ? director.get(0) : null;
	}
	
	
	
}
