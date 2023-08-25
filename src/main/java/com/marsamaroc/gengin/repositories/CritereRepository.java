package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Critere;
import com.marsamaroc.gengin.models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritereRepository extends JpaRepository<Critere, Long> {
}
