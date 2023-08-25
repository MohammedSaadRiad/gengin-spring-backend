package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// @JsonIdentityInfo(scope=Engin.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )

public class Engin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomEngin;
    private String codeEngin;
    //@PositiveOrZero(message = "Les heures comptées doivent etre positives")
    private Long Computer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "famille_id")
    private Famille famille;

    private Boolean EnPanne;



}
