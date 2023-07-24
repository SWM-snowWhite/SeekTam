package food.backend.search.repository;

import food.backend.search.entity.FoodMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMainRepository extends JpaRepository<FoodMain, Long> {
    FoodMain findByFoodId(Long foodId);
}