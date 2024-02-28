package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {

    @Autowired
    EstateRepository estateRepository;

    public void addEstate(Estate estate){
        estateRepository.save(estate);
    }
    
    public List<Estate> getAllEstates(){
        return estateRepository.findAll();
    }
}
