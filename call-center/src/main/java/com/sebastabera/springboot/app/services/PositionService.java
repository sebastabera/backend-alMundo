package com.sebastabera.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastabera.springboot.app.dao.IPositionDaoRepository;
import com.sebastabera.springboot.app.models.entity.Position;

@Service
@Transactional
public class PositionService {
	
	@Autowired
	private IPositionDaoRepository positionRepository;
	
	public void save(Position position) {
		positionRepository.save(position);
	}
	
	
	
}
