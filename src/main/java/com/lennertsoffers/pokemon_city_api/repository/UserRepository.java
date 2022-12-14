package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
