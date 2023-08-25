package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.CheckedCritere;
import com.marsamaroc.gengin.models.ControlledEngin;
import com.marsamaroc.gengin.models.Panne;
import com.marsamaroc.gengin.repositories.*;
import com.marsamaroc.gengin.services.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/panne")
public class PanneController {
    @Autowired
    PanneRepository panneRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    EnginRepository enginRepository;
    @Autowired
    ControlledEnginRepository controlledEnginRepository;
    @Autowired
    CheckedCritereRepository checkedCritereRepository;

    @Transactional
    @PostMapping
    public Panne AddPanne(@RequestBody Panne ReceivedPanne) {
        // Save the ControlledEngin first
        ControlledEngin controlledEngin = controlledEnginRepository.save(ReceivedPanne.getEnginEnPanne());

        // Save the CheckedCritere entities
        for (CheckedCritere checkedCritere : controlledEngin.getCheckedCriteres()) {
            // Set the saved ControlledEngin to the CheckedCritere
            checkedCritere.setControlledEngin(controlledEngin);
            // Save the CheckedCritere
            checkedCritereRepository.save(checkedCritere);
        }

        // Set the saved ControlledEngin to the Panne
        ReceivedPanne.setEnginEnPanne(controlledEngin);

        // Save the Panne
        Panne savedPanne = panneRepository.save(ReceivedPanne);

        // Return the saved Panne
        return savedPanne;
    }


    @GetMapping
    public List<Panne> ReturnAllPanne() {
        return panneRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void DeletePanne(@PathVariable Long id) {

        panneRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Panne ReparerPanne(@PathVariable Long id,@RequestBody Panne ReceivedPanne) {

        Panne PanneAReparer = panneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Demande not found with numBCI " + id));

        PanneAReparer.setRepare(true);
        PanneAReparer.setDescriptionReparation(ReceivedPanne.getDescriptionReparation());
        System.out.println(ReceivedPanne.getDescriptionReparation());
        Date DateReparation = new Date();
        PanneAReparer.setDateReparation(DateReparation);

        return panneRepository.save(PanneAReparer);

    }

}
