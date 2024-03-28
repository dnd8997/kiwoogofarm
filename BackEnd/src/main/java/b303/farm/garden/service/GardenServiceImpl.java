package b303.farm.garden.service;

import b303.farm.garden.entity.Garden;
import b303.farm.garden.repository.GardenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class GardenServiceImpl implements GardenService {
    private final GardenRepository gardenRepository;

    @Override
    public List<Garden> getAllGardens() {
        return gardenRepository.findAll();
    }
}