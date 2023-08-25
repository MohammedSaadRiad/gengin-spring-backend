package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.CheckedCritere;
import com.marsamaroc.gengin.models.Critere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckedCritereRepository extends JpaRepository<CheckedCritere, Long> {
}
