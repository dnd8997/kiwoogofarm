package b303.farm.crop.repository;

import b303.farm.crop.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findByNameContaining(String name);

    // 카테고리로 농작물 검색
    List<Crop> findByCategory(String category);
}
