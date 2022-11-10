package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.Statistics;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserCreationDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserFilterDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserUpdateStatisticsDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.UserMapper;
import com.lennertsoffers.pokemon_city_api.repository.CityRepository;
import com.lennertsoffers.pokemon_city_api.repository.RoleRepository;
import com.lennertsoffers.pokemon_city_api.repository.UserRepository;
import com.lennertsoffers.pokemon_city_api.security.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static com.lennertsoffers.pokemon_city_api.security.RoleType.PLAYER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final StatisticsService statisticsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found!");

        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User register(UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.username(), userCreationDto.password());
        Statistics statistics = new Statistics();
        statistics.setUser(user);
        City city = new City();
        city.setName(userCreationDto.username() + " city");
        city.setDateCreated(LocalDate.now());
        city.setUser(user);

        this.saveUser(user);
        statisticsService.saveStatistics(statistics);
        cityRepository.save(city);

        return this.addRoleToUser(userCreationDto.username(), PLAYER);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User addRoleToUser(String username, RoleType roleType) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleType.fullName());

        user.getRoles().add(role);

        return user;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<UserDataDto> getRanking(Integer min, Integer amount) {
        List<User> users = this.userRepository.findAll();
        int skip = Math.max(min == null ? 0 : min - 1, 0);
        int limit = (amount == null ? users.size() : amount);

        return users
                .stream()
                .filter(user -> user.getStatistics() != null)
                .sorted((a, b) -> {
                    long diff = b.getStatistics().getScore() - a.getStatistics().getScore();
                    if (diff > 0) return 1;
                    if (diff == 0) return 0;
                    return -1;
                })
                .skip(skip)
                .limit(limit)
                .map(userMapper::toUserDataDto)
                .toList();
    }

    @Override
    public List<UserDataDto> getFiltered(UserFilterDto filter) {
        Predicate<User> filterPredicate = this.getFilterPredicate(filter.field(), filter.operation(), filter.value());

        return this.userRepository
                .findAll()
                .stream()
                .filter(user -> user.getCity() != null)
                .filter(filterPredicate)
                .map(userMapper::toUserDataDto)
                .toList();
    }

    @Override
    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return getUser(username);
    }

    @Override
    public UserDataDto getUserData() {
        return userMapper.toUserDataDto(this.getAuthUser());
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateStatistics(UserUpdateStatisticsDto userUpdateStatisticsDto) {
        this.getAuthUser().getStatistics().updateTimePlayed(userUpdateStatisticsDto.sessionTime());
    }

    private Predicate<User> getFilterPredicate(String field, String operation, String value) {
        if (field.equals("username")) return user -> user.getUsername().startsWith((value));

        return user -> {
            Long checkedValue = switch (field) {
                case "score" -> user.getStatistics().getScore();
                case "level" -> (long) user.getLevel();
                default -> null;
            };

            if (checkedValue == null) return false;
            if (!value.matches("[0-9]+")) return false;

            Long longValue = Long.parseLong(value);

            return switch (operation) {
                case ">" -> checkedValue > longValue;
                case ">=" -> checkedValue >= longValue;
                case "<" -> checkedValue < longValue;
                case "<=" -> checkedValue <= longValue;
                case "==" -> checkedValue.equals(longValue);
                default -> false;
            };
        };

    }
}
