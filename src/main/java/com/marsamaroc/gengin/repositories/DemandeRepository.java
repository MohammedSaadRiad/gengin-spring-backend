package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long> {

    List<Demande> findByUtilisateurId(Long utilisateurid);

    @Query("SELECT d FROM Demande d WHERE " +
            "(:enAttente is null or d.EtatDemande = 'En Attente') or " +
            "(:acceptee is null or d.EtatDemande = 'Acceptée') or " +
            "(:refusee is null or d.EtatDemande = 'Refusée')")
    List<Demande> findWithFilters(@Param("enAttente") Boolean enAttente, @Param("acceptee") Boolean acceptee, @Param("refusee") Boolean refusee);
}
