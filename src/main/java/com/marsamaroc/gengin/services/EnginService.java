package com.marsamaroc.gengin.services;


import com.marsamaroc.gengin.models.Engin;
import com.marsamaroc.gengin.models.Famille;
import com.marsamaroc.gengin.repositories.EnginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnginService {

    @Autowired
    private EnginRepository enginRepository;



    public List<Engin> getAllEngins() {
        return enginRepository.findAll();
    }

    public Engin getEnginById(Long id) throws ResourceNotFoundException {
        return enginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found with id " + id));
    }

    public Engin createEngin(Engin engin) {
        return enginRepository.save(engin);
    }

    public Engin updateEngin(Long id, Engin engin) throws ResourceNotFoundException {
        Engin existingEngin = enginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found with id " + id));

        existingEngin.setNomEngin(engin.getNomEngin());
        existingEngin.setCodeEngin(engin.getCodeEngin());
        existingEngin.setComputer(engin.getComputer());
        existingEngin.setFamille(engin.getFamille());

        return enginRepository.save(existingEngin);
    }

    public void deleteEngin(Long id) throws ResourceNotFoundException {
        Engin engin = enginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engin not found with id " + id));
        enginRepository.delete(engin);
    }

    public void addEngin(){
        Famille famille1 = new Famille();
        famille1.setCodeFamille("Famille1");
        famille1.setNomFamille("Nom de la famille 1");
        famille1.setImageUrl("http://image.com/famille1.png");

        Famille famille2 = new Famille();
        famille2.setCodeFamille("Famille2");
        famille2.setNomFamille("Nom de la famille 2");
        famille2.setImageUrl("http://image.com/famille2.png");

        Engin engin1 = new Engin();
        engin1.setNomEngin("Engin 1");
        engin1.setCodeEngin("E1");
        engin1.setComputer(12345L);
        engin1.setFamille(famille1);

        Engin engin2 = new Engin();
        engin2.setNomEngin("Engin 2");
        engin2.setCodeEngin("E2");
        engin2.setComputer(67890L);
        engin2.setFamille(famille2);

        enginRepository.save(engin1);
        enginRepository.save(engin2);
    }
}
