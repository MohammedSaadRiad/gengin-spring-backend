package com.marsamaroc.gengin.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@EqualsAndHashCode(exclude = {"EnginEnPanne"})
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private boolean Repare;

    @CreationTimestamp
    private Date DatePanne;

    private Date DateReparation;


    @OneToOne
    @JoinColumn(name = "id_controlledEngin")
   private ControlledEngin EnginEnPanne;

   private String DescriptionPanne;

   private String DescriptionReparation;



}
