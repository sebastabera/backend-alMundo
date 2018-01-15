package com.sebastabera.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastabera.springboot.app.dao.IEmployeeDaoRepository;
import com.sebastabera.springboot.app.models.entity.Employee;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private IEmployeeDaoRepository employeeRepository;
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
}
