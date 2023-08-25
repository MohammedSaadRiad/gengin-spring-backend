package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Utilisateur;
import com.marsamaroc.gengin.repositories.UtilisateurRepository;
import com.marsamaroc.gengin.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
   @Autowired
   private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/allUsers")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateur);
    }



    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur Receivedutilisateur) {
        Utilisateur existingUtilisateur = utilisateurService.getUtilisateurById(id);

        if(Receivedutilisateur.getActive() != null){
            existingUtilisateur.setActive(Receivedutilisateur.getActive());
        }
        if(Receivedutilisateur.getMatricule() != null){
            existingUtilisateur.setMatricule(Receivedutilisateur.getMatricule());
        }
        if(Receivedutilisateur.getEntite() != null){
            existingUtilisateur.setEntite(Receivedutilisateur.getEntite());
        }
        if(Receivedutilisateur.getNom() != null){
            existingUtilisateur.setNom(Receivedutilisateur.getNom());
        }
        if(Receivedutilisateur.getIdentifiant()!= null){
            existingUtilisateur.setIdentifiant(Receivedutilisateur.getIdentifiant());
        }
        if(Receivedutilisateur.getPrenom()!= null){
            existingUtilisateur.setPrenom(Receivedutilisateur.getPrenom());
        }
        if(Receivedutilisateur.getMotDePasse()!= null){
            existingUtilisateur.setMotDePasse(Receivedutilisateur.getMotDePasse());
        }
        if(Receivedutilisateur.getSociete()!= null){
            existingUtilisateur.setSociete(Receivedutilisateur.getSociete());
        }


        return utilisateurRepository.save(existingUtilisateur);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        Utilisateur existingUtilisateur = utilisateurService.getUtilisateurById(id);
        if (existingUtilisateur != null) {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

