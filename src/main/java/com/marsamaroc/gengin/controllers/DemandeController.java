package com.marsamaroc.gengin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsamaroc.gengin.models.*;
import com.marsamaroc.gengin.repositories.DemandeRepository;
import com.marsamaroc.gengin.repositories.EnginsAffectesRepository;
import com.marsamaroc.gengin.services.DemandeService;
import com.marsamaroc.gengin.services.DetailsDemandesService;
import com.marsamaroc.gengin.services.ShiftService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/demandes")
public class DemandeController {
    private final DemandeService demandeService;
    private final ShiftService shiftService;
    private DetailsDemandesService detailsDemandesService;
    private EnginsAffectesRepository enginsAffectesRepository;
    private DemandeRepository demandeRepository;

    public DemandeController(DemandeService demandeService, ShiftService shiftService, DetailsDemandesService detailsDemandesService, EnginsAffectesRepository enginsAffectesRepository) {
        this.demandeService = demandeService;

        this.shiftService = shiftService;
        this.detailsDemandesService = detailsDemandesService;
        this.enginsAffectesRepository = enginsAffectesRepository;
    }

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


    @PostMapping("{demandeId}/AffectEngins")
    public Demande AffectEnginsToDemande(@PathVariable Long demandeId,@RequestBody List<Engin> ReceivedEngins){
        EnginAffectes EnginAffectesToAssignToDemande = EnginAffectes.builder().EnginsAffectes(ReceivedEngins).build();

        EnginAffectes EnginAffectesToAssignToDemandeCreated = enginsAffectesRepository.save(EnginAffectesToAssignToDemande);
        Demande DemandeToAssignTo = demandeService.getDemandeById(demandeId);
        DemandeToAssignTo.setEnginsAffecteALaDemande(EnginAffectesToAssignToDemandeCreated);

        Demande DemandeToAssignToSaved = createDemande(DemandeToAssignTo);

        return DemandeToAssignToSaved;
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

        return demandeService.createDemande(DemandeTargeted);

    }
    // @PutMapping("/{demandeId}")
  //  public Demande setShiftofDemande (@RequestBody List<Long>ShiftID @PathVariable Long demandeId){

    // }
}
