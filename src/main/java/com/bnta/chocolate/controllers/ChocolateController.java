package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.services.ChocolateService;
import com.bnta.chocolate.services.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

    @Autowired
    EstateService estateService;

    @GetMapping
    public ResponseEntity<List<Chocolate>> getAllChocolates(){
        return new ResponseEntity<>(chocolateService.getAllChocolates(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Chocolate> getChocolateById(@PathVariable long id){
        Optional<Chocolate> chocolate = chocolateService.getChocolateById(id);
        if(chocolate.isPresent()) {
            return new ResponseEntity<>(chocolate.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/darker{cocoaPercentage}")
    public ResponseEntity<List<Chocolate>> getDarkerChocolate(@PathVariable int cocoaPercentage){
        return new ResponseEntity<>(chocolateService.darkAsHeckChocolate(cocoaPercentage), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Chocolate> addNewChocolate(@RequestBody ChocolateDTO chocolate){
        System.out.println(chocolate.getEstateId());
        Optional<Estate> estate = estateService.getEstateById(chocolate.getEstateId());
        System.out.println(estate);
        if (estate.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Chocolate newChocolate = new Chocolate(chocolate.getName(), chocolate.getCocoaPercentage(), estate.get());
        chocolateService.addChocolate(newChocolate);
        return new ResponseEntity<>(newChocolate,HttpStatus.CREATED);
    }
}
