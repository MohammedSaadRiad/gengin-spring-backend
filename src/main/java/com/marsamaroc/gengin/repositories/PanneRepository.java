package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Famille;
import com.marsamaroc.gengin.models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanneRepository extends JpaRepository<Panne, Long> {
}
