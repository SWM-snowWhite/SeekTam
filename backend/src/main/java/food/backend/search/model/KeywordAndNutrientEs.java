package food.backend.search.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Data
public class KeywordAndNutrientEs {

    @NotNull
    @NotBlank
    private String keyword;

    private String calorie;
    private String calorieCon;

    private String carbohydrate;
    private String carbohydrateCon;

    private String protein;
    private String proteinCon;

    private String fat;
    private String fatCon;

    public KeywordAndNutrientEs(String kcal, String kcal_con, String carb, String carb_con, String prot,
                                String prot_con, String fat, String fat_con) {
        this.calorie = kcal;
        this.calorieCon = kcal_con;
        this.carbohydrate = carb;
        this.carbohydrateCon = carb_con;
        this.protein = prot;
        this.proteinCon = prot_con;
        this.fat = fat;
        this.fatCon = fat_con;
    }
}
