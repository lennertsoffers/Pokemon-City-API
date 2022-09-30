package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.IncomeBuilding;
import com.lennertsoffers.pokemon_city_api.repository.IncomeBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomeBuildingServiceImpl implements IncomeBuildingService {
    private final IncomeBuildingRepository incomeBuildingRepository;

    @Override
    public List<IncomeBuilding> getAllFromUser(Long id) {
        return incomeBuildingRepository.getAllFromUser(id);
    }

    @Override
    public Integer collect(Long id) {
        IncomeBuilding incomeBuilding = incomeBuildingRepository.findIncomeBuildingById(id);
        if (incomeBuilding == null) return null;

        return incomeBuilding.collect();
    }
}
