package food.backend.search.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class FoodMaterialEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodMaterialId;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodMainEntity foodId;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity materialEntity;

    public Long getMaterialId() {
        return materialEntity.getMaterialId();
    }

}
