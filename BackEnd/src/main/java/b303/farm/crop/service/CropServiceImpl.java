package b303.farm.crop.service;

import b303.farm.crop.controller.CropController;
import b303.farm.crop.entity.Crop;

import b303.farm.crop.repository.CropRepository;
import b303.farm.exception.RecipeNotFoundException;
import b303.farm.mypage.CropFavorites;
import b303.farm.mypage.FavoriteCropRepository;
import b303.farm.user.User;
import b303.farm.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;
//    private final CropDetailRepository cropDetailRepository;
    private final UserRepository userRepository;
    private final FavoriteCropRepository favoriteCropRepository;

    private static final Logger log = LoggerFactory.getLogger(CropServiceImpl.class);

//    public CropServiceImpl(CropDetailRepository cropDetailRepository, UserRepository userRepository, FavoriteCopRepository favoriteCropRepository) {
//        this.cropDetailRepository = cropDetailRepository;
//        this.userRepository = userRepository;
//        this.favoriteCropRepository = favoriteCropRepository;
//    }

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

    @Override
    public List<Crop> searchCropsByCategory(String category) {
        return cropRepository.findByCategory(category);
    }


    @Override
//    @Transactional  //class단위로 넣어야 되는지?
    public String favoriteCrop(Long id,String email){
        Crop crop = cropRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

        //db에서 crop,user가 둘다 같은 데이터가 있는지 조회한다(true,false불문)
        CropFavorites cropFavorites = favoriteCropRepository.findByCropAndUser(crop, currentUser);
        //만약 없다면, 생성하고 저장한다.
        if(cropFavorites == null){
            cropFavorites = CropFavorites.createCropFavorites(crop, currentUser);
            favoriteCropRepository.save(cropFavorites);
            return "즐겨찾기 신규설정";  //다른 값?
        } else {
            //만약 존재한다면
            //그리고 status값이 true라면, status를 false로 바꾸고,연관관계도 끊고,좋아요수도 증감시킨다.
            if (cropFavorites.isStatus()){
                cropFavorites.unFavoriteCrop();
//                favoriteRecipeRepository.save(recipeFavorites);

                log.info("리스트 확인 " + currentUser.getCropFavoritesList());//.get(0).getId()
                return "즐겨찾기 취소";
            } else {
                //status값이 false라면,status를 true로 바꾸고, 연관관계도 만들고, 좋아요 수도 증가시킨다.
                cropFavorites.reFavoriteCrop();
//                favoriteCropRepository.save(cropFavorites);
                log.info("리스트 확인" + currentUser.getCropFavoritesList());//.get(0).getId()
                return "즐겨찾기 재설정";
            }
        }
    }

    @Override
//    @Transactional
    public List<Crop> getMyFavoriteCrops(String email) {
        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<CropFavorites> myFavoriteCropList = favoriteCropRepository.findAllByUserAndStatus(currentUser, true);
        log.info("즐겨찾기리스트 확인" + currentUser.getCropFavoritesList());//.get(0).getId()
        List<Crop> myFavoriteCrops = new ArrayList<>();
        for (CropFavorites cropFavorites : myFavoriteCropList) {
            myFavoriteCrops.add(cropFavorites.getCrop());
        }
        return myFavoriteCrops;
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