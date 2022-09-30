package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Company")
public class Company extends IncomeBuilding {
    @Enumerated
    private CompanyType companyType;

    @Override
    public int collect() {
        long rentMinutes = this.getRentMinutes();
        super.collect();

        int rent = (int) (rentMinutes * this.getProfitPerMinute());
        this.getCity().getUser().addMoney(rent);

        return rent;
    }

    @Override
    public String getName() {
        return this.companyType.getName();
    }

    @Override
    public int getSatisfactionModifier() {
        return this.companyType.getSatisfactionModifier();
    }

    @Override
    public int getXpWhenFinished() {
        return this.companyType.getXpWhenFinished();
    }

    @Override
    public int getPrice() {
        return this.companyType.getPrice();
    }

    @Override
    public int getUnlockedAtLevel() {
        return this.companyType.getUnlockedAtLevel();
    }

    @Override
    public int getHeight() {
        return this.companyType.getHeight();
    }

    @Override
    public int getWidth() {
        return this.companyType.getWidth();
    }

    public int getProfitPerMinute() {
        return this.companyType.getProfitPerMinute();
    }

    private int getTotalProfit() {
        // TODO - Implement method
        return 0;
    }
}
