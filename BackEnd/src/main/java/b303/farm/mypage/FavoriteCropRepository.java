package b303.farm.mypage;

import b303.farm.crop.entity.Crop;
import b303.farm.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCropRepository extends JpaRepository<CropFavorites,Long> {
        CropFavorites findByCropAndUser(Crop crop, User user);


        //    CropFavorites findByCrop(Crop crop);
        List<CropFavorites> findAllByUser(User user);

        List<CropFavorites> findAllByUserAndStatus(User user, boolean status);

    }
