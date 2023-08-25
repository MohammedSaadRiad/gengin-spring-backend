package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckedCritere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_critere")
    private Critere critere;
    private boolean EnMarche;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "controlledEngin_id")
    private ControlledEngin ControlledEngin;
}
