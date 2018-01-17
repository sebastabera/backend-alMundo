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
	
	private Call call;
	
	private int numValidCalls;
	
	private int numInvalidCalls;
	
	public Dispatcher() {
		this.numInvalidCalls = 0;
		this.numValidCalls = 0;
	}
	
	/*
	 * Metodo que permite crear un nuevo hilo de llamada al call center
	 */
	public void createCall(CountDownLatch cdl) throws InterruptedException {
		if(attendCall()) {
			int numero = (int) (Math.random() * 5) + 5;
			call.setTime(numero);
			callDaoRepository.save(call);
			TCall newCall = new TCall(call, numero, cdl);
			Thread llamada = new Thread(newCall);			
			llamada.start();
			numValidCalls++;
		} else {			
			numInvalidCalls++;
		}
	}
	
	/*
	 * metodo que permite verificar si se cumplen diferentes condiciones para recibir llamadas
	 * validaciones como el numero maximo de llamadas o si hay usuarios disponibles para atender las llamadas
	 */
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
			System.out.println("Hay 10 llamadas en curso, no se puede atender la nueva llamada");
			return false;
		}
	}
	
	/*
	 * metodo que permite instanciar una llamada si encuentra empleados disponibles
	 */
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
	
	/*
	 * metodo para verificar si se ha llegado al numero maximo de llamadas que se pueden atender
	 */
	public boolean verifyMaxNumberCalls() {
		List<Call> calls = (List<Call>) callDaoRepository.findAll();
		return (calls != null && calls.size() <10) ? true : false;
	}
	
	/*
	 * metodo que permite encontrar un empleado para atender una llamada
	 */
	public Employee employeeToCall() {		
		List<Employee> operador = employeeDaoRepository.findByPosition("operador");
		List<Employee> supervisor = employeeDaoRepository.findByPosition("supervisor");
		List<Employee> director = employeeDaoRepository.findByPosition("director");
		return (operador.size() != 0) ? operador.get(0) : (supervisor.size() != 0) ? supervisor.get(0) : (director.size() != 0) ? director.get(0) : null;
	}

	public int getNumValidCalls() {
		return numValidCalls;
	}

	public void setNumValidCalls() {
		this.numValidCalls = 0;
	}
	
	public int getNumInvalidCalls() {
		return numInvalidCalls;
	}
	
	public void setNumInvalidCalls() {
		this.numInvalidCalls = 0;
	}
	
}
