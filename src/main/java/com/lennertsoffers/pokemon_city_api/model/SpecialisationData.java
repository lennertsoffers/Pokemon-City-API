package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialisationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int value;

    @Enumerated(EnumType.STRING)
    private SpecialisationType specialisationType;

    @OneToOne
    private Citizen citizen;
}
