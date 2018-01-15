package com.sebastabera.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.sebastabera.springboot.app.models.entity.Position;

public interface IPositionDaoRepository extends CrudRepository<Position, Long>{

}
