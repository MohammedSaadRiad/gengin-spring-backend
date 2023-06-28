package com.marsamaroc.gengin.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsamaroc.gengin.models.DetailsDemandes;
import com.marsamaroc.gengin.models.Famille;
import com.marsamaroc.gengin.services.DetailsDemandesService;
import com.marsamaroc.gengin.services.FamilleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/detailsdemandes")
public class DetailsDemandesController {

    private final ObjectMapper objectMapper;

    public DetailsDemandesController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    private DetailsDemandesService detailsDemandesService;
    @Autowired
    private FamilleService familleService;



    @GetMapping
    public ResponseEntity<List<DetailsDemandes>> getAllDetailsDemandes() {
        List<DetailsDemandes> detailsDemandesList = detailsDemandesService.getAllDetailsDemandes();
        return ResponseEntity.ok(detailsDemandesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsDemandes> getDetailsDemandesById(@PathVariable Long id) {
        DetailsDemandes detailsDemandes = detailsDemandesService.getDetailsDemandesById(id);
        return ResponseEntity.ok(detailsDemandes);
    }

    @PostMapping("/addDetailDemand")
    public ResponseEntity<DetailsDemandes> createDetailsDemandes(@RequestBody DetailsDemandes detailsDemandes) {
        DetailsDemandes createdDetailsDemandes = detailsDemandesService.createDetailsDemandes(detailsDemandes);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDetailsDemandes);
    }




    @PostMapping("/postDetailsDemandes")
    public Long CreateDetailsDemande_ReturnItsID(@RequestBody JsonNode jsonPayload) {


        Long familleId = jsonPayload.get("familleid").asLong();
        int quantite = jsonPayload.get("quantite").asInt();

      Famille FamilletoSetforDetailsDemande = familleService.getFamilleById(familleId);
        DetailsDemandes detailsDemandeToCreate = DetailsDemandes.builder().famille(FamilletoSetforDetailsDemande)
                .quantite(quantite).build();

        detailsDemandesService.createDetailsDemandes(detailsDemandeToCreate);

        return detailsDemandeToCreate.getIdDetailsDemande();}

    @PutMapping("/{id}")
    public ResponseEntity<DetailsDemandes> updateDetailsDemandes(
            @PathVariable Long id, @RequestBody DetailsDemandes updatedDetailsDemandes) {
        DetailsDemandes updatedEntity = detailsDemandesService.updateDetailsDemandes(id, updatedDetailsDemandes);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailsDemandes(@PathVariable Long id) {
        detailsDemandesService.deleteDetailsDemandes(id);
        return ResponseEntity.noContent().build();
    }
}
