package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Company")
@Getter
@Setter
@NoArgsConstructor
public class Company extends IncomeBuilding {
    public Company(Location location, CompanyType companyType) {
        this.setLocation(location);
        this.setCompanyType(companyType);
    }

    @Enumerated
    private CompanyType companyType;

    @Enumerated
    private BuildableTypeEnum buildableTypeEnum = BuildableTypeEnum.COMPANY;

    @Override
    public Integer collect() {
        long rentMinutes = this.getRentMinutes();

        int rent = (int) Math.round(rentMinutes * this.getProfitPerMinute() * this.getCity().getAmountOfCitizens() * this.getCity().getSatisfaction());

        this.getCity().getUser().addMoney(rent);
        super.collect();

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
