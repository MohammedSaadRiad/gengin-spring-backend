package com.marsamaroc.gengin.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.marsamaroc.gengin.models.Utilisateur;
import com.marsamaroc.gengin.repositories.UtilisateurRepository;
import com.marsamaroc.gengin.returnclasses.LoginSuccesfulOrNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @PostMapping

    public LoginSuccesfulOrNot SignInAndReturnUserObject (@RequestBody JsonNode UsernameAndPwd) {



        String motDePasseReceived = UsernameAndPwd.get("motDePasse").asText();
        String identifiantReceived = UsernameAndPwd.get("identifiant").asText();

        Utilisateur SupposedUserToLogin = utilisateurRepository.findByIdentifiantAndMotDePasse(identifiantReceived,motDePasseReceived);

        if(SupposedUserToLogin == null) {
            LoginSuccesfulOrNot loginSuccesfulOrNot = new LoginSuccesfulOrNot(false,SupposedUserToLogin);
            return loginSuccesfulOrNot;
        }
        else {

           LoginSuccesfulOrNot loginSuccesfulOrNot = new LoginSuccesfulOrNot(true,SupposedUserToLogin);
            return loginSuccesfulOrNot;
        }

    }





}
