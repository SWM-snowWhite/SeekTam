package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 컨트롤러에서 getFoodDetail 메소드의 리턴값으로 사용되는 객체<br>
 * 식품 상세정보를 담고 있음
 */
@Builder
@Data
public class FoodDetailDto {
    private Long foodId;
    private String foodCd;
    private String foodNm;
    private double enerc;
//    private String nutConSrtrQua;
    private double water;
    private double prot;
    private double fatce;
    private double ash;
    private double chocdf;
    private double sugar;
    private double fibtg;
    private double ca;
    private double fe;
    private double p;
    private double k;
    private double nat;
    private double vitaRae;
    private double retol;
    private double cartb;
    private double thia;
    private double ribf;
    private double nia;
    private double vitc;
    private double vitd;
    private double chole;
    private double fasat;
    private double fatrn;
//    private double refuse;
    private String foodSize;
//    private String cooName;
    private String companyName;
}
