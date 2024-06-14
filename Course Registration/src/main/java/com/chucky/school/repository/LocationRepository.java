package com.chucky.school.repository;

import com.chucky.school.domain.Location;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

}
