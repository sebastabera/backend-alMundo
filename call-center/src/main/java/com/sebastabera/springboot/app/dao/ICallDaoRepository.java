package com.sebastabera.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebastabera.springboot.app.models.entity.Call;

public interface ICallDaoRepository extends CrudRepository<Call, Long>{
	
	@Query("SELECT c FROM Call c WHERE c.state = true")
	public List<Call> findByStateCalls();
	
}
