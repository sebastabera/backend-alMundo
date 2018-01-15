package com.sebastabera.springboot.app.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebastabera.springboot.app.models.entity.Employee;

public interface IEmployeeDaoRepository extends CrudRepository<Employee, Long>{
	
	@Query("select e from Employee e join fetch e.position p where p.name like ?1 AND e.id not in (select em.id from Call c join c.employee em where c.state = true)")
	public Employee findByPosition(String typeEmployee);
		
}
