package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserUpdateStatisticsDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.UserMapper;
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
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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
}
