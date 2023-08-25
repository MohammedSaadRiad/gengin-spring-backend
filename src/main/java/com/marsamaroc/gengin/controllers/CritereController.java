package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Critere;
import com.marsamaroc.gengin.repositories.CritereRepository;
import com.marsamaroc.gengin.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/critere")
public class CritereController {
    @Autowired
    CritereRepository critereRepository;

    @PostMapping
    public Critere AddCritere (@RequestBody Critere ReceivedCritere){

        return critereRepository.save(ReceivedCritere);
    }

    @GetMapping
    public List<Critere> ReturnAllCriteres () {

        return critereRepository.findAll();
    }

    @PutMapping("/{id}")
    public Critere ModCritere (@PathVariable Long id,  @RequestBody Critere UpdatedCritere) throws ResourceNotFoundException {

        Critere ExistingCritere = critereRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Critere not found with id " + id));

        if (UpdatedCritere.getNomCritere() != null) {
            ExistingCritere.setNomCritere(UpdatedCritere.getNomCritere());
          }

        return critereRepository.save(ExistingCritere);
}
    @DeleteMapping("/{id}")
    public void DeleteCritere (@PathVariable Long id) {
        critereRepository.deleteById(id);
    }




}
