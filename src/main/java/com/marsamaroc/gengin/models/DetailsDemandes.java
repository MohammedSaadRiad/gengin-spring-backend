package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
//@Table(name = "details_demande")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDetailsDemande")

public class DetailsDemandes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailsDemande;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "demande_numbci")
    private Demande demande;

    @OneToOne
    @JoinColumn(name ="famille_id")
    private Famille famille;


}
