package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.time.LocalTime;

import org.joda.time.LocalTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(scope = Shift.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeShift;
    private String heureDebut;
    private String heureFin;



}
