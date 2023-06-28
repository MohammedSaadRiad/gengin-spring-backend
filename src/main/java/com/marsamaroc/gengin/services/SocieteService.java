package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.repositories.SocieteRepository;
import com.marsamaroc.gengin.models.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocieteService {
    private final SocieteRepository societeRepository;

    public SocieteService(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    public Societe createSociete(Societe societe) {
        return societeRepository.save(societe);
    }

    public Societe getSocieteById(Long id) {
        return societeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Societe not found with id: " + id));
    }

    public List<Societe> getAllSocietes() {
        return societeRepository.findAll();
    }

    public Societe updateSociete(Long id, Societe updatedSociete) {
        Societe existingSociete = getSocieteById(id);
        existingSociete.setCodeSociete(updatedSociete.getCodeSociete());
        existingSociete.setNomSociete(updatedSociete.getNomSociete());
        existingSociete.setActive(updatedSociete.getActive());
        existingSociete.setDateCreation(updatedSociete.getDateCreation());
        existingSociete.setDerniereModification(updatedSociete.getDerniereModification());
        return societeRepository.save(existingSociete);
    }

    public void deleteSociete(Long id) {
        societeRepository.deleteById(id);
    }
}



