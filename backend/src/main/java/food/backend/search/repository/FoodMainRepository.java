package food.backend.search.repository;

import food.backend.search.entity.FoodMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMainRepository extends JpaRepository<FoodMainEntity, Long> {
    FoodMainEntity findByFoodId(Long foodId);
}