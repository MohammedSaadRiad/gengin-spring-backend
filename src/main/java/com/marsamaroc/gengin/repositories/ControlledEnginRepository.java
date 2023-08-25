package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.ControlledEngin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ControlledEnginRepository extends JpaRepository<ControlledEngin, Long>  {
}
