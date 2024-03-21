package b303.farm.crop.service;

import b303.farm.crop.entity.Crop;
import b303.farm.crop.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public Crop getCropDetails(Long id) {
        return cropRepository.findById(id).orElse(null);
    }

    //검색
    @Override
    public List<Crop> searchCrops(String name) {
        return cropRepository.findByNameContaining(name);
    }

//    @Override
//    public List<Crop> recommendCrops(RecommendationRequest request) {
//        // 추천 로직 구현
//        return new ArrayList<>();
//    }

//    @Override
//    public String likeCrop(LikeRequest request) {
//        // 찜 변경 로직 구현
//        return "Success";
//    }
}