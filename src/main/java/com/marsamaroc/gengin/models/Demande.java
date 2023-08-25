package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(scope = Demande.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "numBCI")
public class Demande implements Serializable {


    @Column(name = "demande_numbci")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numBCI;
    //@NotBlank(message = "La date de sortie est necessaire")
    //@FutureOrPresent(message ="La date de sortie prevue ne peut pas etre au passé")
    private Date dateSortie;
    @CreationTimestamp
    private Date dateCreation;
    private Date dernierModification;
    private String EtatDemande;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="utilisateurid")
    private Utilisateur utilisateur;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "demande_numbci")
    List<DetailsDemandes> detailsDemandeList;

    @ManyToOne
    @JoinColumn(name = "id_entite")
    private Entite entite;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "id_shift")
    private Shift shift;

    @OneToMany(mappedBy = "Demande", cascade = CascadeType.PERSIST)
    private List<ControlledEngin> enginsAssignes;



}
