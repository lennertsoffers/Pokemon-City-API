package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.Company;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.CitizenMapper;
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
    private static final int SPECIALISATION_DATA_VALUE_MULTIPLIER = 12;
    private static final int SPECIALISATION_DATA_VALUE_MAX = 30;
    private static final int MAX_SPECIALISATION_DATA_VALUE_MULTIPLIER = 40;
    private static final int MAX_SPECIALISATION_DATA_VALUE_MAX = 100;
    private static final List<String> DEFAULT_NAMES = List.of(
            "James",
            "Mary",
            "Robert",
            "Patricia",
            "John",
            "Jennifer",
            "Michael",
            "Linda",
            "David",
            "Elizabeth",
            "William",
            "Barbara",
            "Richard",
            "Susan",
            "Joseph",
            "Jessica",
            "Thomas",
            "Sarah",
            "Charles",
            "Karen",
            "Christopher",
            "Lisa",
            "Daniel",
            "Nancy",
            "Matthew",
            "Betty",
            "Anthony",
            "Margaret",
            "Mark",
            "Sandra",
            "Donald",
            "Ashley",
            "Steven",
            "Kimberly",
            "Paul",
            "Emily",
            "Andrew",
            "Donna",
            "Joshua",
            "Michelle",
            "Kenneth",
            "Carol",
            "Kevin",
            "Amanda",
            "Brian",
            "Dorothy",
            "George",
            "Melissa",
            "Timothy",
            "Deborah",
            "Ronald",
            "Stephanie",
            "Edward",
            "Rebecca",
            "Jason",
            "Sharon",
            "Jeffrey",
            "Ryan",
            "Jacob",
            "Gary",
            "Nicholas",
            "Eric",
            "Jonathan",
            "Stephen",
            "Larry",
            "Justin",
            "Scott",
            "Brandon",
            "Benjamin",
            "Samuel",
            "Gregory",
            "Alexander",
            "Frank",
            "Patrick",
            "Raymond",
            "Jack",
            "Dennis",
            "Jerry",
            "Tyler",
            "Aaron",
            "Jose",
            "Adam",
            "Nathan",
            "Henry",
            "Douglas",
            "Zachary",
            "Peter",
            "Kyle",
            "Ethan",
            "Walter",
            "Noah",
            "Jeremy",
            "Christian",
            "Keith",
            "Roger",
            "Terry",
            "Gerald",
            "Harold",
            "Sean",
            "Austin",
            "Carl",
            "Arthur",
            "Lawrence",
            "Dylan",
            "Jesse",
            "Jordan",
            "Bryan",
            "Billy",
            "Joe",
            "Bruce",
            "Gabriel",
            "Logan",
            "Albert",
            "Willie",
            "Alan",
            "Juan",
            "Wayne",
            "Elijah",
            "Randy",
            "Roy",
            "Vincent",
            "Ralph",
            "Eugene",
            "Russell",
            "Bobby",
            "Mason",
            "Philip",
            "Louis",
            "Elena",
            "Lea"
    );

    private final CitizenRepository citizenRepository;
    private final BuildableRepository buildableRepository;
    private final UserService userService;
    private final CitizenMapper citizenMapper;

    @Override
    public List<CitizenDto> getAllFromCurrentUser() {
        return citizenRepository
                .getAllFromUser(userService.getAuthUser().getId())
                .stream()
                .peek(Citizen::update)
                .map(citizenMapper::toCitizenDto)
                .toList();
    }

    @Override
    public List<CitizenDto> getUnassignedCitizensFromCurrentUser() {
        return citizenRepository
                .getAllUnassignedFromUser(userService.getAuthUser().getId())
                .stream()
                .peek(Citizen::update)
                .map(citizenMapper::toCitizenDto)
                .toList();
    }

    @Override
    public void spawnCitizen(City city) {
        Random random = new Random();

        // Create a new citizen object
        Citizen citizen = new Citizen();
        // Assign a random name
        citizen.setName(DEFAULT_NAMES.get(random.nextInt(DEFAULT_NAMES.size())));
        // Set the city of the citizen to the city where it should be spawned
        citizen.setCity(city);
        // Generate two maps of specialisationData
        // - One for the starting values of the citizen
        // - One for the max values of the specialisation data
        Map<SpecialisationType, Integer> specialisationData = this.generateSpecialisationData(SPECIALISATION_DATA_VALUE_MULTIPLIER, SPECIALISATION_DATA_VALUE_MAX);
        Map<SpecialisationType, Integer> maxSpecialisationData = this.generateSpecialisationData(MAX_SPECIALISATION_DATA_VALUE_MULTIPLIER, MAX_SPECIALISATION_DATA_VALUE_MAX);

        // Merge the two maps of specialisation data
        for (SpecialisationType specialisationType : SpecialisationType.values()) {
            // The value of the specialisation data cannot be higher than the max for this specialisation
            int minValue = Math.min(specialisationData.get(specialisationType), maxSpecialisationData.get(specialisationType));
            specialisationData.put(specialisationType, minValue);
        }

        // Set the specialisationData
        citizen.setSpecialisationData(specialisationData);
        // Set the maxSpecialisationData
        citizen.setMaxSpecialisationData(maxSpecialisationData);
        // Generate and set a random level speed for the citizen
        citizen.setLevelSpeed((int) Math.round(Math.abs(random.nextGaussian() * 2)) + 1);

        // Save the citizen to the database
        citizenRepository.save(citizen);
    }

    @Override
    public void killCitizen(Long id) {
        citizenRepository.deleteById(id);
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

    /**
     * Generates a map of SpecialisationData for each literal of the SpecialisationType enum
     * @param multiplier Value to multiply with the RNG
     * @param maxValue The max value of specialisation
     * @return The map with the generated SpecialisationData
     */
    private Map<SpecialisationType, Integer> generateSpecialisationData(int multiplier, int maxValue) {
        Random random = new Random();
        Map<SpecialisationType, Integer> specialisationData = new HashMap<>();

        // Generate a value for each type in the SpecialisationType enum
        for (SpecialisationType specialisationType : SpecialisationType.values()) {
            int value = (int) Math.min(Math.abs(Math.round(random.nextGaussian() * multiplier)), maxValue);

            specialisationData.put(specialisationType, value);
        }

        return specialisationData;
    }
}
