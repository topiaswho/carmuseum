package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long>{

	Location findByLocationName(String string);
	
}
