package com.marsamaroc.gengin.controllers;

import com.marsamaroc.gengin.models.Shift;
import com.marsamaroc.gengin.services.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/shifts")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/addShift")
   public ResponseEntity<Shift> createShift(@RequestBody Shift shift) {
       Shift createdShift = shiftService.createShift(shift);
       return new ResponseEntity<>(createdShift, HttpStatus.CREATED);
   }

  /*  @PostMapping
    public ResponseEntity<Shift> createShift(@RequestBody Shift ReceivedShift) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");


        String HeureDebutReceived = String.valueOf(ReceivedShift.getHeureDebut());
        String HeureFinToReceived = String.valueOf(ReceivedShift.getHeureFin());
        LocalTime HeureDebutToCreate = dtf.parseLocalTime(HeureDebutReceived);
        LocalTime HeureFinToCreate = dtf.parseLocalTime(HeureFinToReceived);
        Shift createdShift = new Shift();
        createdShift.setCodeShift(ReceivedShift.getCodeShift());
        createdShift.setHeureDebut(HeureDebutToCreate);
        createdShift.setHeureFin(HeureFinToCreate);


        shiftService.createShift(createdShift);
        return new ResponseEntity<>(createdShift, HttpStatus.CREATED);
    }
*/

  /*  @GetMapping("/{id}")
    public ResponseEntity<Shift> getShiftById(@PathVariable Long id) {
        Shift shift = shiftService.getShiftById(id);
        return new ResponseEntity<>(shift, HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public Shift getShiftById(@PathVariable Long id) {

        return shiftService.getShiftById(id);
    }


    @GetMapping
    public ResponseEntity<List<Shift>> getAllShifts() {
        List<Shift> shifts = shiftService.getAllShifts();
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shift> updateShift(@PathVariable Long id, @RequestBody Shift updatedShift) {
        Shift shift = shiftService.updateShift(id, updatedShift);
        return new ResponseEntity<>(shift, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
