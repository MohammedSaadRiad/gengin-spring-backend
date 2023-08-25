package com.marsamaroc.gengin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marsamaroc.gengin.controllers.PanneController;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"Panne"})
public class ControlledEngin {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne(cascade = CascadeType.MERGE)
     @JoinColumn(name = "id_engin")
     private Engin enginControlé;
     @OneToMany(mappedBy = "ControlledEngin",cascade = CascadeType.PERSIST)
     private List<CheckedCritere> checkedCriteres;
     /*@JsonIgnore
     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "id_panne")
     private Panne Panne;*/
     @JsonIgnore
     @ManyToOne
     @JoinColumn(name = "numbci_demande")
     private Demande Demande;

}
