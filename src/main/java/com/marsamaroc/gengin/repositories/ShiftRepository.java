package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {
    
}
