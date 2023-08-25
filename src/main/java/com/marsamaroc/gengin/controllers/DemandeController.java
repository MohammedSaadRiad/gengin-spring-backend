package com.marsamaroc.gengin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsamaroc.gengin.models.*;
import com.marsamaroc.gengin.repositories.CheckedCritereRepository;
import com.marsamaroc.gengin.repositories.ControlledEnginRepository;
import com.marsamaroc.gengin.repositories.DemandeRepository;
import com.marsamaroc.gengin.repositories.EnginRepository;
import com.marsamaroc.gengin.services.DemandeService;
import com.marsamaroc.gengin.services.DetailsDemandesService;
import com.marsamaroc.gengin.services.ResourceNotFoundException;
import com.marsamaroc.gengin.services.ShiftService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/demandes")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private  ShiftService shiftService;
    @Autowired
    private DetailsDemandesService detailsDemandesService;

    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private EnginRepository enginRepository;
    @Autowired
    private ControlledEnginRepository controlledEnginRepository;
    @Autowired
    private CheckedCritereRepository checkedCritereRepository;





    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    @GetMapping("/{utilisateurid}")
    public List<Demande> getDemandesByUserId(@PathVariable Long utilisateurid) {
        return demandeService.getDemandeByUserId(utilisateurid);
    }

    @PostMapping("/addDemand")
    public Demande createDemande(@Valid @RequestBody Demande demande) {
        return demandeService.createDemande(demande);
    }


    @DeleteMapping("/{demandeId}")
    public void deleteDemand(@PathVariable Long demandeId) {
        demandeService.deleteDemande(demandeId);
    }

    @Transactional
    @PostMapping("/Demande+ShiftID+DetailsDemandeIDs")
    public Demande TakeListOfDetailsDemandesIDs_TakeShiftIDs_TakeDemandeObject_FillDemandeAndSave(@RequestBody JsonNode jsonPayload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Long> ReceivedDetailsDemandeIds = new ArrayList<>();
        jsonPayload.get("detailsDemandeIds").forEach(node -> ReceivedDetailsDemandeIds.add(node.asLong()));

        Demande receivedDemande = mapper.treeToValue(jsonPayload.get("demande"), Demande.class);
        Long ReceivedShiftId = jsonPayload.get("shiftId").asLong();
        Shift Shift_ToSet = shiftService.getShiftById(ReceivedShiftId);
        List<DetailsDemandes> DetailsDemandeList_ToSet = detailsDemandesService.findAllDetailsDemandesByID(ReceivedDetailsDemandeIds);
        receivedDemande.setShift(Shift_ToSet);
        receivedDemande.setDetailsDemandeList(DetailsDemandeList_ToSet);
        receivedDemande.setEtatDemande("En Attente");



        Demande SavedDemande = demandeService.createDemande(receivedDemande);

        return SavedDemande;}


    @Transactional

    @PutMapping("{numBCI}/AffectEngins")
    public ResponseEntity<Demande> addEnginsToDemand(@PathVariable Long numBCI, @RequestBody List<ControlledEngin> engins) {
        // Find the demande with the numBCI
        Demande demande = demandeRepository.findById(numBCI) .orElseThrow(() -> new ResourceNotFoundException("Demande not found with numBCI " + numBCI));

        // Save each ControlledEngin object to the database
        List<ControlledEngin> managedEngins = new ArrayList<>();
        for (ControlledEngin engin : engins) {
            for (CheckedCritere checkedCritere : engin.getCheckedCriteres()) {
                checkedCritere.setControlledEngin(engin);
            }
            engin.setDemande(demande);

            managedEngins.add(controlledEnginRepository.save(engin));

        }

        demande.setEnginsAssignes(managedEngins);

        demande.setEtatDemande("Acceptée");

        // Return the updated Demande
        return ResponseEntity.ok(demandeRepository.save(demande));
    }


    @PostMapping("{demandeId}/RefuserDemande")
    public Demande RefuserDemande (@PathVariable Long demandeId){

        Demande DemandeTargeted =  demandeService.getDemandeById(demandeId);

        DemandeTargeted.setEtatDemande("Refusée");

        return demandeService.createDemande(DemandeTargeted);

    }

    @PostMapping("{demandeId}/AccepterDemande")
    public Demande AcecepterDemande (@PathVariable Long demandeId){

        Demande DemandeTargeted =  demandeService.getDemandeById(demandeId);

        DemandeTargeted.setEtatDemande("Acceptée");

        return demandeService.createDemande(DemandeTargeted);

    }

    @PostMapping("{demandeId}/AttendreDemande")
    public Demande AttendreDemande (@PathVariable Long demandeId){

        Demande DemandeTargeted =  demandeService.getDemandeById(demandeId);

        DemandeTargeted.setEtatDemande("En Attente");

        if(DemandeTargeted.getEnginsAssignes() != null) {
            for (ControlledEngin controlledEngin : DemandeTargeted.getEnginsAssignes()) {
               controlledEngin.setDemande(null);
               for(CheckedCritere checkedCritere :controlledEngin.getCheckedCriteres()){
                   checkedCritere.setControlledEngin(null);
                   checkedCritere.setCritere(null);
                   checkedCritereRepository.delete(checkedCritere);
               }

              controlledEngin.setCheckedCriteres(null);
               controlledEngin.setEnginControlé(null);
               controlledEnginRepository.delete(controlledEngin);



            }
            DemandeTargeted.setEnginsAssignes(null);
        }

        return demandeService.createDemande(DemandeTargeted);

    }

    public void setEnginRepository(EnginRepository enginRepository) {
        this.enginRepository = enginRepository;
    }

    @GetMapping("/filter")
    public List<Demande> getDemandesByFilters(
            @RequestParam(required = false) Boolean enAttente,
            @RequestParam(required = false) Boolean acceptee,
            @RequestParam(required = false) Boolean refusee
    ) {
        System.out.println("enAttente: " + enAttente);
        System.out.println("acceptee: " + acceptee);
        System.out.println("refusee: " + refusee);
        return demandeRepository.findWithFilters(enAttente, acceptee, refusee);
    }


    // @PutMapping("/{demandeId}")
  //  public Demande setShiftofDemande (@RequestBody List<Long>ShiftID @PathVariable Long demandeId){

    // }
}
