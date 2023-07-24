package food.backend.search.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class FoodMaterial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodMaterialId;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodMain foodId;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    public Long getMaterialId() {
        return material.getMaterialId();
    }

}
