package b303.farm.service;

import b303.farm.entity.Crop;

import java.util.List;

public interface CropService {
    List<Crop> getAllCrops();
    Crop getCropDetails(Long id);
//    List<Crop> recommendCrops(RecommendationRequest request);
//    String likeCrop(LikeRequest request);
}
