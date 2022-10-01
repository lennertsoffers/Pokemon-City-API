package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.Company;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import com.lennertsoffers.pokemon_city_api.repository.BuildableRepository;
import com.lennertsoffers.pokemon_city_api.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
    private static final List<String> DEFAULT_NAMES = List.of(
            "Villager",
            "Hendrik",
            "Johan"
    );

    private final CitizenRepository citizenRepository;
    private final BuildableRepository buildableRepository;
    private final UserService userService;

    @Override
    public List<Citizen> getAllFromCurrentUser() {
        return citizenRepository.getAllFromUser(userService.getAuthUser().getId());
    }

    @Override
    public Citizen spawnCitizen(City city) {
        Random random = new Random();

        Citizen citizen = new Citizen();
        citizen.setName(DEFAULT_NAMES.get(random.nextInt(0, DEFAULT_NAMES.size())));
        citizen.setCity(city);
        Map<SpecialisationType, Integer> specialisationData = this.generateSpecialisationData(15, 50);
        Map<SpecialisationType, Integer> maxSpecialisationData = this.generateSpecialisationData(40, 100);

        for (SpecialisationType specialisationType : SpecialisationType.values()) {
            int minValue = Math.min(specialisationData.get(specialisationType), maxSpecialisationData.get(specialisationType));
            specialisationData.put(specialisationType, minValue);
        }

        citizen.setSpecialisationData(specialisationData);
        citizen.setMaxSpecialisationData(maxSpecialisationData);

        return citizenRepository.save(citizen);
    }

    @Override
    public boolean assignToCompany(CitizenAssignmentDto citizenAssignmentDto) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(citizenAssignmentDto.companyId());
        Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenAssignmentDto.citizenId());

        if (optionalBuildable.isEmpty() || optionalCitizen.isEmpty()) return false;
        if (optionalBuildable.get() instanceof Company company) {
            Citizen citizen = optionalCitizen.get();

            return citizen.assignToCompany(company);
        }

        return false;
    }

    @Override
    public boolean unAssign(Long citizenId) {
        Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenId);

        if (optionalCitizen.isEmpty()) return false;
        Citizen citizen = optionalCitizen.get();

        return citizen.unEmploy();
    }

    @Override
    public boolean belongsToUser(Long id) {
        Optional<Citizen> citizen = citizenRepository.findById(id);
        if (citizen.isEmpty()) return false;

        return citizen.get().getCity().getUser().getId().equals(userService.getAuthUser().getId());
    }

    private Map<SpecialisationType, Integer> generateSpecialisationData(int multiplier, int maxValue) {
        Random random = new Random();
        Map<SpecialisationType, Integer> specialisationData = new HashMap<>();

        for (SpecialisationType specialisationType : SpecialisationType.values()) {
            int value = (int) Math.min(Math.abs(Math.round(random.nextGaussian() * multiplier)), maxValue);

            specialisationData.put(specialisationType, value);
        }

        return specialisationData;
    }
}
