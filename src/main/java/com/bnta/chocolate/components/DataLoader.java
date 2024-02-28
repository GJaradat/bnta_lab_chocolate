package com.bnta.chocolate.components;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.services.ChocolateService;
import com.bnta.chocolate.services.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ChocolateService chocolateService;

    @Autowired
    EstateService estateService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Estate estate1 = new Estate("Chocoville", "Ecuador");
        Estate estate2 = new Estate("Count Chocula's", "Transylvania");
        Estate estate3 = new Estate("McChocghees", "Scotland");

        estateService.addEstate(estate1);
        estateService.addEstate(estate2);
        estateService.addEstate(estate3);

        chocolateService.addChocolate(new Chocolate("Ach!", 40, estate3));
        chocolateService.addChocolate(new Chocolate("Terry's chocolate", 50, estate3));
        chocolateService.addChocolate(new Chocolate("Arriba", 90, estate1));
        chocolateService.addChocolate(new Chocolate("Andale", 80, estate1));
        chocolateService.addChocolate(new Chocolate("Milka", 30, estate2));
    }

}
