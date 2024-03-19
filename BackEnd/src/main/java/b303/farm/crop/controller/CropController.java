package b303.farm.crop.controller;

import b303.farm.crop.entity.Crop;
import b303.farm.crop.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    // 농작물 전체 조회
    @GetMapping("/all")
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    // 농작물 상세 조회
    @GetMapping("/{id}")
    public Crop getCropDetails(@PathVariable("id") Long id) {
        return cropService.getCropDetails(id);
    }


//    // 농작물 추천
//    @PostMapping("/recommend")
//    public List<Crop> recommendCrops(@RequestBody RecommendationRequest request) {
//        return cropService.recommendCrops(request);
//    }
//
//    // 농작물 찜 변경
//    @PostMapping("/like")
//    public String likeCrop(@RequestBody LikeRequest request) {
//        return cropService.likeCrop(request);
//    }
}
