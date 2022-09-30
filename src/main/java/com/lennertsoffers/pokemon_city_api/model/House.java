package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("House")
@Getter
@Setter
@NoArgsConstructor
public class House extends IncomeBuilding {
    public House(HouseType houseType, Location location) {
        this.setLocation(location);
        this.setHouseType(houseType);
    }

    @Enumerated
    private HouseType houseType;

    @Enumerated
    private BuildableTypeEnum buildableTypeEnum = BuildableTypeEnum.HOUSE;

    @Override
    public int collect() {
        long rentMinutes = this.getRentMinutes();
        super.collect();

        int rent = (int) (rentMinutes * this.getRentPerMinute());
        if (rent > this.getMaxRent()) {
            rent = this.getMaxRent();
        }

        this.getCity().getUser().addMoney(rent);

        return rent;
    }

    @Override
    public String getName() {
        return this.houseType.getName();
    }

    @Override
    public int getSatisfactionModifier() {
        return this.houseType.getSatisfactionModifier();
    }

    @Override
    public int getXpWhenFinished() {
        return this.houseType.getXpWhenFinished();
    }

    @Override
    public int getPrice() {
        return this.houseType.getPrice();
    }

    @Override
    public int getUnlockedAtLevel() {
        return this.houseType.getUnlockedAtLevel();
    }

    @Override
    public int getHeight() {
        return this.houseType.getHeight();
    }

    @Override
    public int getWidth() {
        return this.houseType.getWidth();
    }

    public int getNumberOfCitizens() {
        return this.houseType.getNumberOfCitizens();
    }

    public int getMaxRent() {
        return this.houseType.getMaxRent();
    }

    public int getRentPerMinute() {
        return this.houseType.getRentPerMinute();
    }
}
