package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.models.DetailsDemandes;
import com.marsamaroc.gengin.repositories.DetailsDemandesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsDemandesService {

    @Autowired
    private DetailsDemandesRepository detailsDemandesRepository;

    public List<DetailsDemandes> getAllDetailsDemandes() {
        return detailsDemandesRepository.findAll();
    }

    public DetailsDemandes getDetailsDemandesById(Long id) {
        return detailsDemandesRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("DetailsDemandes not found with id: " + id,502));
    }

    public DetailsDemandes createDetailsDemandes(DetailsDemandes detailsDemandes) {
        return detailsDemandesRepository.save(detailsDemandes);
    }

    public DetailsDemandes updateDetailsDemandes(Long id, DetailsDemandes updatedDetailsDemandes) {
        DetailsDemandes existingDetailsDemandes = detailsDemandesRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("DetailsDemandes not found with id: " + id,503));

        existingDetailsDemandes.setQuantite(updatedDetailsDemandes.getQuantite());


        // Update other fields as needed

        return detailsDemandesRepository.save(existingDetailsDemandes);
    }

    public List<DetailsDemandes> findAllDetailsDemandesByID (List<Long> detailsDemandeIds){

        return detailsDemandesRepository.findAllById(detailsDemandeIds);
    }

    public void deleteDetailsDemandes(Long id) {
        detailsDemandesRepository.deleteById(id);
    }
}
