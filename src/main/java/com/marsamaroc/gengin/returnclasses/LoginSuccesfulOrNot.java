package com.marsamaroc.gengin.returnclasses;

import com.marsamaroc.gengin.models.Utilisateur;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginSuccesfulOrNot {

    private Utilisateur ReturnedUserWhenSuccessLogin;
    private boolean UserFound;
    private String Message;

    public LoginSuccesfulOrNot(boolean userFound, Utilisateur returnedUserWhenSuccessLogin) {
        this.UserFound = userFound;
        if (userFound) {
            this.Message = "Connexion Réussite";
            this.ReturnedUserWhenSuccessLogin = returnedUserWhenSuccessLogin;
        } else {
            this.Message = "Mot de Passe ou Identifiant Incorrect";
            this.ReturnedUserWhenSuccessLogin = null;
        }
    }
}




