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

/**
 * <b>Represents a company in the city</b>
 * <p>Companies generate profit over time (depending on the employees and amount of citizens in the city)</p>
 * <p>Citizens can be assigned to companies so they'll generate more money</p>
 * @see IncomeBuilding
 */
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

    /**
     * <p>Collects the accumulated money since last collection and add it to the user to which this building belongs to</p>
     * @return The collected amount of money
     */
    @Override
    public Integer collect() {
        long minutesSinceLastCollection = this.getMinutesSinceLastCollection();
        double incomePerMinute = this.getIncomePerMinute();

        // Profit is the income per minute of the company times the minutes since the last collection
        int profit = (int) (minutesSinceLastCollection * incomePerMinute);

        // Add the money to current user
        this.getCity().getUser().addMoney(profit);

        super.collect();
        super.updateStatisticMoneyCollected(profit);

        return profit;
    }

    /**
     * <p>
     *     Calculates and returns the income that this company will generate in one minute
     * </p>
     * <p>
     *     The formula used for this is:<br/>
     *     Income Per Minute = profit per min * citizenMultiplier * satisfaction * employee multiplier
     *     <ul>
     *         <li>Profit per minute: Amount of profit this company generates in one minute (static)</li>
     *         <li>CitizenMultiplier: 1 + (citizens in city / 100) => Each citizen gives 0.01 more profit</li>
     *         <li>Satisfaction: Satisfaction of the city + 1.5 (city satisfaction ranges from -1 until 1 => +1.5 because we cannot multiply by 0)</li>
     *         <li>Employee multiplier</li>
     *     </ul>
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

    /**
     * Calculates the sum of the specialisationData of all the employees of this company
     * @return A double representing the multiplier that employees give this company
     */
    public double getEmployeeMultiplier() {
        double multiplier = this.getAssignedCitizens()
                .stream()
                .mapToDouble(citizen -> citizen.getSpecialisationData().get(this.getSpecialisationType()) / 100F)
                .sum();

        return 1 + multiplier;
    }

    /**
     * @return true if there is a free employee space in the company
     */
    public boolean isFullyAssigned() {
        return this.assignedCitizens.size() >= this.getMaxAssignedCitizens();
    }

    /**
     * Un-assigns all the citizens assigned to this company
     */
    public void unEmployAll() {
        for (Iterator<Citizen> iterator = this.getAssignedCitizens().iterator(); iterator.hasNext();) {
            Citizen citizen = iterator.next();
            citizen.assignNull();
            iterator.remove();
        }
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

    /**
     * Assign a citizen to this company
     * @param citizen The citizen that will be assigned to this company
     */
    protected void assign(Citizen citizen) {
        // Return if there are no free spaces for employees
        if (this.isFullyAssigned()) return;
        this.getAssignedCitizens().add(citizen);
    }

    /**
     * Un-assigns a citizen from this company
     * @param citizenId The id of the citizen that has to be unemployed
     */
    protected void unAssing(Long citizenId) {
        this.getAssignedCitizens().removeIf(citizen -> citizen.getId().equals(citizenId));
    }
}
