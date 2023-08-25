package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Engin;
import com.marsamaroc.gengin.models.Famille;
import com.marsamaroc.gengin.repositories.EnginRepository;
import com.marsamaroc.gengin.services.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/engins")
public class EnginController {

    @Autowired
    private EnginRepository enginRepository;


    // Get all engins
    @GetMapping
    public List<Engin> getAllEngins() {
        return enginRepository.findAll();
    }

    // Get engin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Engin> getEnginById(@PathVariable(value = "id") Long enginId)
            throws ResourceNotFoundException {
        Engin engin = enginRepository.findById(enginId)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found for this id :: " + enginId));
        return ResponseEntity.ok().body(engin);
    }

    // Create engin
    @PostMapping("/createEngin")
    public Engin createEngin(@RequestBody Engin engin) {
        return enginRepository.save(engin);
    }

    // Update engin

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Engin> updateEngin(@PathVariable(value = "id") Long enginId,
                                              @RequestBody Engin enginDetails) throws ResourceNotFoundException {
        Engin engin = enginRepository.findById(enginId)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found for this id :: " + enginId));
       if(enginDetails.getNomEngin() != null) {
        engin.setNomEngin(enginDetails.getNomEngin());}
       if(enginDetails.getCodeEngin() != null){
        engin.setCodeEngin(enginDetails.getCodeEngin());}
        if(enginDetails.getFamille() != null ){
            engin.setFamille(enginDetails.getFamille());}
        final Engin updatedEngin = enginRepository.save(engin);
        return ResponseEntity.ok(updatedEngin);
    }

    // Delete engin
    @DeleteMapping("/deleteEngin/{id}")
    public Map<String, Boolean> deleteEngin(@PathVariable(value = "id") Long enginId)
            throws ResourceNotFoundException {
        Engin engin = enginRepository.findById(enginId)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found for this id :: " + enginId));
        enginRepository.delete(engin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

