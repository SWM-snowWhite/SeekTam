package food.backend.search.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class KeywordAndNutrient {

    @NotNull @NotBlank
    private String keyword;
    private String enerc;
    private String enercCon;
    private String chocdf;
    private String chocdfCon;
    private String prot;
    private String protCon;
    private String fatce;
    private String fatceCon;

    public KeywordAndNutrient(String enerc_con, String chocdf_con, String prot_con, String fatce_con) {
        this.enercCon = enerc_con;
        this.chocdfCon = chocdf_con;
        this.protCon = prot_con;
        this.fatceCon = fatce_con;
    }
}
