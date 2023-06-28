package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Demande;
import com.marsamaroc.gengin.models.DetailsDemandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsDemandesRepository extends JpaRepository<DetailsDemandes, Long> {
   List<DetailsDemandes> findByDemande(Demande demande);
    // Add more custom query methods if needed
}
