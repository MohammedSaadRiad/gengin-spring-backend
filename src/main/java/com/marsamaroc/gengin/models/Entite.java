package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Entite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeEntite;
    private String nomEntite;


    @OneToMany(mappedBy = "entite")
    @JsonIgnore
    private List<Utilisateur> utilisateurList;

    @OneToMany(mappedBy = "entite")
    private List<Demande> demandeList;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    private Societe societe;


}
