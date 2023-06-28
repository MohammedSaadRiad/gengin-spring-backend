package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
@Data
@Entity
public class Societe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String codeSociete;
    private String nomSociete;

    private Boolean active = true;
    @CreationTimestamp
    private Date dateCreation;
    private Date derniereModification;

    @OneToMany(mappedBy = "societe")
    private List<Entite> entiteList;
}
