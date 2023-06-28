package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.models.Demande;
import com.marsamaroc.gengin.repositories.DemandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {
    private final DemandeRepository demandeRepository;

    //initialiser la demande
    public DemandeService(DemandeRepository demandeRepository){
        this.demandeRepository= demandeRepository;
    }

    //recupere tous les demandes
    public List<Demande> getAllDemandes(){
        return demandeRepository.findAll();
    }

    //recupere les demandes par user id
    public List<Demande> getDemandeByUserId(Long utilisateurid){
        return demandeRepository.findByUtilisateurId(utilisateurid);
    }

    //creer demande
    public Demande createDemande(Demande demande){
        return demandeRepository.save(demande);

    }

    //supprimer demande
    public void deleteDemande(Long demandeId){
        demandeRepository.deleteById(demandeId);
    }
    

    //mise a jour a notre demande
    public  Demande updateDemand(Long demandeId,Demande demande){

        Optional<Demande> checkedDemande= demandeRepository.findById(demandeId);
        if(checkedDemande.isPresent()){
            Demande updateDemande = checkedDemande.get();
            updateDemande.setNumBCI(demande.getNumBCI());
            return demandeRepository.save(updateDemande);

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Demand not found with id " + demandeId);
        }

    }


}
