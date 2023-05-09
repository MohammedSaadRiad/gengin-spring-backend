package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String matricule;
    private String nom;
    private String prenom;
    private String identifiant;
    private String motDePasse;
    private Boolean active;
    private String type;
    private Date date;
    private Date dernierModification;

    @OneToMany(mappedBy = "utilisateur")
    private List<Demande> listDemande;

    @ManyToOne
    private Entite entite;






}
