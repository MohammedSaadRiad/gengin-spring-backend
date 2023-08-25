package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Societe;
import com.marsamaroc.gengin.services.SocieteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/societes")
public class SocieteController {
    private final SocieteService societeService;

    public SocieteController(SocieteService societeService) {
        this.societeService = societeService;
    }

    @PostMapping("/addSociete")
    public ResponseEntity<Societe> createSociete(@RequestBody Societe societe) {

        Societe createdSociete = societeService.createSociete(societe);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSociete);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Societe> getSocieteById(@PathVariable Long id) {
        Societe societe = societeService.getSocieteById(id);
        return new ResponseEntity<>(societe, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Societe>> getAllSocietes() {
        List<Societe> societes = societeService.getAllSocietes();
        return new ResponseEntity<>(societes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Societe> updateSociete(@PathVariable Long id, @RequestBody Societe updatedSociete) {

        Societe societe = societeService.updateSociete(id, updatedSociete);
        return new ResponseEntity<>(societe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        societeService.deleteSociete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
