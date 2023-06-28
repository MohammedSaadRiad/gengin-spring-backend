package com.marsamaroc.gengin.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.marsamaroc.gengin.models.Utilisateur;
import com.marsamaroc.gengin.repositories.UtilisateurRepository;
import com.marsamaroc.gengin.services.UtilisateurService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @PostMapping

    public Utilisateur SignInAndReturnUserObject (@RequestBody JsonNode UsernameAndPwd) {

        String motDePasseReceived = UsernameAndPwd.get("motDePasse").asText();
        String identifiantReceived = UsernameAndPwd.get("identifiant").asText();

        return utilisateurRepository.findByIdentifiantAndMotDePasse(identifiantReceived,motDePasseReceived);



    }

}
