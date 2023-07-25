package food.backend.search.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class FoodMain {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    private Long reportNo;
    private String foodName;
    private String foodImage;
    private String purchaseLink;
    private Integer haccpStatus;

    @OneToMany(mappedBy = "foodId")
    private List<FoodMaterial> foodMaterial;


}
