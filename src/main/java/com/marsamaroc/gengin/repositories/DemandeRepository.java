package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long> {

    List<Demande> findByUtilisateurId(Long utilisateurid);


}
