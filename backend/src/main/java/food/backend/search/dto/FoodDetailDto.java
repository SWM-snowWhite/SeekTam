package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ID : ST-C-110-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
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
