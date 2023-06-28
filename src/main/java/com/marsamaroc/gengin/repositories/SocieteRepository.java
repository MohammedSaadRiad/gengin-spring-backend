package com.marsamaroc.gengin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marsamaroc.gengin.models.Societe;

@Repository
public interface SocieteRepository extends JpaRepository<Societe,Long> {
    
}
