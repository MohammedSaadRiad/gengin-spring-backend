package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {
    List<Entite> findByCodeEntite(String codeEntite);
    // recupere entite par code
}