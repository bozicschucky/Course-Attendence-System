package com.chucky.school.repository;

import com.chucky.school.config.OpenAPIConfig;
import com.chucky.school.domain.Location;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

    Optional<Location> findById(long id);
    //Collection<Location> findByTypeId(long typeId);

}
