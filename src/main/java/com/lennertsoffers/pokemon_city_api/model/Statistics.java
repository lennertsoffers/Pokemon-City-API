package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int timePlayed = 0;
    private int totalValue = 0;
    private int buildingsBuilt = 0;
    private int moneySpent = 0;
    private int moneyCollected = 0;

    @OneToOne
    private User user;

    public int getProfit() {
        return this.totalValue - this.moneySpent;
    }

    public int getIncomePerMinute() {
        return this.getUser()
                .getCity()
                .getBuildables()
                .stream()
                .filter(buildable -> buildable instanceof IncomeBuilding)
                .mapToInt(incomeBuilding -> {
                    if (incomeBuilding instanceof House house) {
                        return house.getRentPerMinute();
                    } else if (incomeBuilding instanceof Company company) {
                        return company.getProfitPerMinute();
                    }

                    return 0;
                })
                .sum();
    }
}
