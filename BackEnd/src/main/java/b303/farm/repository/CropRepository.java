package b303.farm.repository;

import b303.farm.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    // 필요한 경우 추가 쿼리 메서드를 여기에 정의할 수 있습니다.
}
