package com.marsamaroc.gengin.services;

import com.marsamaroc.gengin.repositories.ShiftRepository;
import com.marsamaroc.gengin.models.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public Shift getShiftById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shift not found with id: " + id));
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift updateShift(Long id, Shift updatedShift) {

        Shift existingShift = getShiftById(id);
        if(updatedShift.getCodeShift() != null ) {
        existingShift.setCodeShift(updatedShift.getCodeShift());}
        if(updatedShift.getHeureDebut() != null ) {
        existingShift.setHeureDebut(updatedShift.getHeureDebut());}
            if(updatedShift.getHeureFin() != null ) {
        existingShift.setHeureFin(updatedShift.getHeureFin());}
        return shiftRepository.save(existingShift);
    }

    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }
}

