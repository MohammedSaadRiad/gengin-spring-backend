package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Engin;
import com.marsamaroc.gengin.models.Famille;
import com.marsamaroc.gengin.services.EnginService;
import com.marsamaroc.gengin.services.FamilleService;
import com.marsamaroc.gengin.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/familles")
public class FamilleController {
      @Autowired
      private EnginService enginService;
    @Autowired
    private FamilleService familleService;

    @GetMapping
    public List<Famille> getAllFamilles() {
        return familleService.getAllFamilles();
    }

    @GetMapping("/{id}")
    public Famille getFamilleById(@PathVariable Long id) throws ResourceNotFoundException {
        return familleService.getFamilleById(id);
    }

    @PostMapping("/addFamille")
    public Famille createFamille(@RequestBody Famille famille) {


        return familleService.createFamille(famille);
    }

    @PutMapping("/{id}")
    public Famille updateFamille(@PathVariable Long id, @RequestBody Famille familleDetails) throws ResourceNotFoundException {
        return familleService.updateFamille(id, familleDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFamille(@PathVariable Long id) throws ResourceNotFoundException {

        Famille familleToDelete = familleService.getFamilleById(id);
        /*for(Engin enginFamille : familleToDelete.getEngins()){
            enginService.deleteEngin(enginFamille.getId());
        }*/
        familleService.deleteFamille(id);
        return ResponseEntity.ok().build();
    }
}

