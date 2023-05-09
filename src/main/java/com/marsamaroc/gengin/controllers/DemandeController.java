package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Demande;
import com.marsamaroc.gengin.services.DemandeService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService){
        this.demandeService= demandeService;

    }

    @GetMapping("")
    public List<Demande> getAllDemandes(){
        return demandeService.getAllDemandes();
    }

    @GetMapping("/{utilisateurid}")
    public List<Demande> getDemandesByUserId(@PathVariable Long utilisateurid){
        return demandeService.getDemandeByUserId(utilisateurid);
    }

    @PostMapping("")
    public Demande createDemande(@RequestBody Demande demande){
        return demandeService.createDemande(demande);
    }

    @DeleteMapping("/{demandeId}")
    public void deleteDemand(@PathVariable Long demandeId){
        demandeService.deleteDemande(demandeId);
    }
}
