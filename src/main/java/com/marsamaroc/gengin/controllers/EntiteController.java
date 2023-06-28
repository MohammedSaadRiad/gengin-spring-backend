package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Entite;
import com.marsamaroc.gengin.services.EntiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/entities")
public class EntiteController {

    @Autowired
    private EntiteService entiteService;

    @GetMapping
    public List<Entite> getAllEntites() {
        return entiteService.getAllEntites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entite> getEntiteById(@PathVariable Long id) {
        Entite entite = entiteService.getEntiteById(id);
        return ResponseEntity.ok(entite);
    }

    @PostMapping("/addEntite")
    public ResponseEntity<Entite> createEntite(@RequestBody Entite entite) {
        Entite createdEntite = entiteService.createEntite(entite);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entite> updateEntite(@PathVariable Long id, @RequestBody Entite entite) {
        Entite updatedEntite = entiteService.updateEntite(id, entite);
        return ResponseEntity.ok(updatedEntite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntite(@PathVariable Long id) {
        entiteService.deleteEntite(id);
        return ResponseEntity.noContent().build();
    }
}
