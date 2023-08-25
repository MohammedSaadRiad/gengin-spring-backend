package com.marsamaroc.gengin.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marsamaroc.gengin.repositories.FamilleRepository;
import com.marsamaroc.gengin.models.Famille;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.*;



import java.util.List;

@Service
public class FamilleService {

    @Autowired
    private FamilleRepository familleRepository;

    public List<Famille> getAllFamilles() {
        return familleRepository.findAll();
    }

    public Famille getFamilleById(Long id){
        Famille famille = familleRepository.findById(id).orElse(null);
        if (famille == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Famille not found with id " + id);
        }
        return famille;
    }

    public Famille createFamille(Famille famille) {
        return familleRepository.save(famille);
    }

    public Famille updateFamille(Long id, Famille familleDetails) throws ResourceNotFoundException {
        Famille famille = familleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Famille not found with id " + id));
        if (familleDetails.getCodeFamille() != null) {
            famille.setCodeFamille(familleDetails.getCodeFamille());
        }
        if (familleDetails.getNomFamille() != null) {
            famille.setNomFamille(familleDetails.getNomFamille());
        }
        if (familleDetails.getImageUrl() != null) {
            famille.setImageUrl(familleDetails.getImageUrl());
        }
        /*if (familleDetails.getEngins() != null) {
            famille.setEngins(familleDetails.getEngins());
        }*/

        return familleRepository.save(famille);
    }

    public void deleteFamille(Long id) throws ResourceNotFoundException {
        Famille famille = familleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Famille not found with id " + id));
        familleRepository.delete(famille);
    }
}

