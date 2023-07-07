package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import com.marsamaroc.gengin.models.Societe;
import org.hibernate.annotations.CreationTimestamp;

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
    private Long id;

    private String matricule;
    private String nom;
    private String prenom;
    private String identifiant;
    private String motDePasse;
    private Boolean active;
    private String type;
    @CreationTimestamp
    private Date date;
    private Date dernierModification;

    @OneToMany(mappedBy = "utilisateur")
    private List<Demande> listDemande;

    @ManyToOne
    @JoinColumn(name = "id_entite")
    private Entite entite;

}
