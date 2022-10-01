package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
        int incomePerMinute = this.getIncomePerMinute();

        int profit = (int) (minutesSinceLastCollection * incomePerMinute);

        this.getCity().getUser().addMoney(profit);
        super.collect();

        return profit;
    }

    @Override
    public int getIncomePerMinute() {
        return (int) Math.round(this.getProfitPerMinute() * this.getCity().getAmountOfCitizens() * this.getCity().getSatisfaction());
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

    public int getMaxAssignedCitizens() {
        return this.companyType.getMaxAssignedCitizens();
    }

    public boolean isAssignable() {
        return this.assignedCitizens.size() < this.getMaxAssignedCitizens();
    }

    protected void assign(Citizen citizen) {
        this.getAssignedCitizens().add(citizen);
    }

    protected void unAssing(Long citizenId) {
        this.getAssignedCitizens().removeIf(citizen -> citizen.getId().equals(citizenId));
    }
}
