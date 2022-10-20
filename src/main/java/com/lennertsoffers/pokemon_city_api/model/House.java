package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * <b>Represents a house in the city</b>
 * <p>Houses generate rent over a period of time, there is a maximum of rent that can be accumulated</p>
 * <p>Houses provide citizens for the city</p>
 * @see IncomeBuilding
 */
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

    /**
     * <p>Collects the accumulated money since last collection and add it to the user to which this building belongs to</p>
     * @return The collected amount of money
     */
    @Override
    public Integer collect() {
        long minutesSinceLastCollection = this.getMinutesSinceLastCollection();
        double incomePerMinute = this.getIncomePerMinute();

        // The rent is the time since the last collection * income per minute
        int rent = (int) (minutesSinceLastCollection * incomePerMinute);

        // You can only collect if the more than half of the max rent is accumulated
        if (rent < this.getMaxRent() / 2) return null;
        // You cannot collect more money than the maxRent
        rent = Math.min(rent, this.getMaxRent());

        // Add the collected money to the user to which this house belongs
        this.getCity().getUser().addMoney(rent);

        super.collect();
        super.updateStatisticMoneyCollected(rent);

        return rent;
    }

    /**
     * <p>Calculates and returns the amount of rent this house will generate in one minute</p>
     * <p>
     *     The formula used for this is:<br/>
     *     Income Per Minute = rent per min * satisfaction
     *     <ul>
     *         <li>Rent per minute: Amount of rent this house generates in one minute (static)</li>
     *         <li>Satisfaction: Satisfaction of the city + 1.5 (city satisfaction ranges from -1 until 1 => +1.5 because we cannot multiply by 0)</li>
     *     </ul>
     * </p>
     * @return The income generated in one minute by this house
     */
    @Override
    public double getIncomePerMinute() {
        return this.getRentPerMinute() * (this.getCity().getSatisfaction() + 1.5);
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

    @Override
    public SpritesheetLocation getSpritesheetLocation() {
        return this.houseType.getSpritesheetLocation();
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
