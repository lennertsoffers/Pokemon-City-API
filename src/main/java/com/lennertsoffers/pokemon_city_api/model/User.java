package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private int level = 1;
    private int xp = 0;
    private int money = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private City city;

    @OneToOne(mappedBy = "user")
    private Statistics statistics;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }
}
