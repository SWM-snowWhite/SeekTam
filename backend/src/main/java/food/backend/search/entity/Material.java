package food.backend.search.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Material {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    private String materialName;
    private Integer ewgScore;
    private Integer ibsFructose;
    private Integer ibsLactose;
    private Integer ibsPolyol;
    private Integer ibsOligo;

}
