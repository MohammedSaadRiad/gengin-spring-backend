package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.models.Utilisateur;
import com.marsamaroc.gengin.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void createUser(String matricule,String nom,String prenom,String identifiant,String motDePasse,String type){
        Utilisateur utilisateur= new Utilisateur();
        utilisateur.setMatricule(matricule);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setIdentifiant(identifiant);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setType(type);
        utilisateurRepository.save(utilisateur);


    }



}
