package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    // TODO - Reset to default for production
    private int xp = 1000;
    private int money = 10000;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private City city;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Statistics statistics;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount) {
        this.money -= amount;
    }

    public void addXp(int amount) {
        this.xp += amount;
    }

    public int getLevel() {
        return (int) Math.floor(Math.pow((this.getXp() / 100F), (1 / 1.2)));
    }
}
