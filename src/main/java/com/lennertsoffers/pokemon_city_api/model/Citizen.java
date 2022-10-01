package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime assignedSince;

    @ElementCollection
    @MapKeyColumn(name="specialisationType")
    @Column(name="value")
    @CollectionTable(name="specialisationData", joinColumns=@JoinColumn(name="id"))
    private Map<SpecialisationType, Integer> specialisationData;

    @ElementCollection
    @MapKeyColumn(name="specialisationType")
    @Column(name="value")
    @CollectionTable(name="maxSpecialisationData", joinColumns=@JoinColumn(name="id"))
    private Map<SpecialisationType, Integer> maxSpecialisationData;

    @ManyToOne
    @JsonBackReference
    private City city;

    @ManyToOne
    @JsonBackReference
    private Company company;
}
