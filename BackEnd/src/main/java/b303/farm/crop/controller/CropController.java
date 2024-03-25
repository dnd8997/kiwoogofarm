package b303.farm.crop.controller;

import b303.farm.crop.dto.CropDto;
import b303.farm.crop.entity.Crop;
import b303.farm.crop.service.CropService;
import b303.farm.recipe.domain.Recipe;
import b303.farm.recipe.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

    private static final Logger log = LoggerFactory.getLogger(CropController.class);

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

    //검색
    @GetMapping("/search")
    public List<Crop> searchCrops(@RequestParam(name = "name") String name) {
        return cropService.searchCrops(name);
    }

    // 카테고리로 농작물 검색
    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Crop>> searchCropsByCategory(@RequestParam(name = "category") String category) {
        List<Crop> crops = cropService.searchCropsByCategory(category);
        if (crops.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }


    //즐겨찾기 설정/해제
    @PostMapping("/{id}/favorites/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> favoriteCrop(@PathVariable("id") final Long id, @PathVariable("email") final String email) {
        String responseMessage = cropService.favoriteCrop(id, email);
        return ResponseEntity.ok(responseMessage);
    }

    //내가 찜한 레시피 조회
    @GetMapping("/myFavorites/{email}")
    public ResponseEntity<List<CropDto>> getMyCropFavorites(@PathVariable("email") final String email) {
        List<CropDto> cropDtoList = new ArrayList<>();
        List<Crop> myFavoriteCrops = cropService.getMyFavoriteCrops(email);

        log.info("유저가 즐겨찾기한 농작물 정보");
        for (Crop c : myFavoriteCrops) {
            CropDto cropDto = CropDto.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .thumbnailUrl(c.getThumbnailUrl())
                    .imageUrl(c.getImageUrl())
                    .category(c.getCategory())
                    .temperature(c.getTemperature())
                    .difficulty(c.getDifficulty())
                    .sunshine(c.getSunshine())
                    .growTime(c.getGrowTime())
                    .height(c.getHeight())
                    .waterPeriod(c.getWaterPeriod())
                    .humidity(c.getHumidity())
                    .isHydroponics(c.getIsHydroponics())
                    .growStart(c.getGrowStart())
                    .waterExit(c.getWaterExit())
                    .cropInfo(c.getCropInfo())
                    .waterPeriodInfo(c.getWaterPeriodInfo())
                    .sunshineInfo(c.getSunshineInfo())
                    .humidityInfo(c.getHumidityInfo())
                    .temperatureInfo(c.getTemperatureInfo())
                    .effectInfo(c.getEffectInfo())
                    .link(c.getLink())
                    .growInfo(c.getGrowInfo())
//                    .likes(c.getLikes())
                    .build();
            log.info("즐겨찾기한 농작물 정보: {}", cropDto);
            cropDtoList.add(cropDto);
        }
        return ResponseEntity.ok().body(cropDtoList);
    }

        //    // 농작물 찜 변경
//    @PostMapping("/like")
//    public String likeCrop(@RequestBody LikeRequest request) {
//        return cropService.likeCrop(request);
//    }
}
