package b303.farm.crop.service;


import b303.farm.crop.entity.Crop;
import b303.farm.recipe.domain.Recipe;

import java.util.List;

public interface CropService {
    List<Crop> getAllCrops();
    Crop getCropDetails(Long id);
    List<Crop> searchCrops(String name);

    String favoriteCrop(Long id,String email);
    List<Crop> getMyFavoriteCrops(String email);

//    List<Crop> recommendCrops(RecommendationRequest request);
//    String likeCrop(LikeRequest request);
}
