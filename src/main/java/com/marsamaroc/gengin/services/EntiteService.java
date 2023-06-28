package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.models.Entite;
import com.marsamaroc.gengin.repositories.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EntiteService {

    @Autowired
    private EntiteRepository entiteRepository;

    public List<Entite> getAllEntites() {
        return entiteRepository.findAll();
    }

    public Entite getEntiteById(Long id) {
        return entiteRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Entite not found with id: " + id,901));
    }

    public Entite createEntite(Entite entite) {
        return entiteRepository.save(entite);
    }

    public Entite updateEntite(Long id, Entite updatedEntite) {
        Entite existingEntite = entiteRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Entite not found with id: " + id,902));

        existingEntite.setCodeEntite(updatedEntite.getCodeEntite());
        existingEntite.setNomEntite(updatedEntite.getNomEntite());

        return entiteRepository.save(existingEntite);
    }

    public void deleteEntite(Long id) {
        try {
            entiteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultDataAccessException("Entite not found with id: " + id, 903);
        }
    }
}

