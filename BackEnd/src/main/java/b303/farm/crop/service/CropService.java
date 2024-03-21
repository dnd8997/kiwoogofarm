package b303.farm.crop.service;


import b303.farm.crop.entity.Crop;

import java.util.List;

public interface CropService {
    List<Crop> getAllCrops();
    Crop getCropDetails(Long id);
    List<Crop> searchCrops(String name);

//    List<Crop> recommendCrops(RecommendationRequest request);
//    String likeCrop(LikeRequest request);
}
