package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;

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

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Citizen> assignedCitizens = new ArrayList<>();

    @Override
    public Integer collect() {
        long minutesSinceLastCollection = this.getMinutesSinceLastCollection();
        double incomePerMinute = this.getIncomePerMinute();

        int profit = (int) (minutesSinceLastCollection * incomePerMinute);

        this.getCity().getUser().addMoney(profit);
        super.collect();
        super.updateStatisticMoneyCollected(profit);

        return profit;
    }

    /**
     * <p>
     * Calculates and returns the income that this company will generate in one minute
     * </p>
     *
     * <p>
     * The formula used for this is:<br/>
     * Income Per Minute = profit per min * (1 + (#citizens / 50)) * (satisfaction + 1.5) * (employee multiplier)
     * </p>
     *
     * @return The income generated in one minute by this company
     */
    @Override
    public double getIncomePerMinute() {
        return this.getProfitPerMinute() *
                (1 + this.getCity().getAmountOfCitizens() / 100F) *
                (this.getCity().getSatisfaction() + 1.5) *
                this.getEmployeeMultiplier();
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

    @Override
    public SpritesheetLocation getSpritesheetLocation() {
        return this.companyType.getSpritesheetLocation();
    }

    public int getProfitPerMinute() {
        return this.companyType.getProfitPerMinute();
    }

    public int getMaxAssignedCitizens() {
        return this.companyType.getMaxAssignedCitizens();
    }

    public SpecialisationType getSpecialisationType() {
        return this.companyType.getSpecialisationType();
    }

    public double getEmployeeMultiplier() {
        double multiplier = this.getAssignedCitizens()
                .stream()
                .mapToDouble(citizen -> citizen.getSpecialisationData().get(this.getSpecialisationType()) / 100F)
                .sum();

        return 1 + multiplier;
    }

    public boolean isAssignable() {
        return this.assignedCitizens.size() < this.getMaxAssignedCitizens();
    }

    public void unEmployAll() {
        for (Iterator<Citizen> iterator = this.getAssignedCitizens().iterator(); iterator.hasNext();) {
            Citizen citizen = iterator.next();
            citizen.assignNull();
            iterator.remove();
        }
    }

    protected void assign(Citizen citizen) {
        this.getAssignedCitizens().add(citizen);
    }

    protected void unAssing(Long citizenId) {
        this.getAssignedCitizens().removeIf(citizen -> citizen.getId().equals(citizenId));
    }
}
