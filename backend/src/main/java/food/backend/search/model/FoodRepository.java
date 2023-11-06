package food.backend.search.model;

import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface FoodRepository extends JpaRepository<ViewsFood, Long> {
    static void increaseViewCount() {
    }
}