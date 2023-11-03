package food.backend.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ID : ST-C-100-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@Data
public class FoodNutritionDto {

    @JsonProperty("foodCd")
    private String foodCd;

    @JsonProperty("foodNm")
    private String foodNm;

    @JsonProperty("dataCd")
    private String dataCd;

    @JsonProperty("typeNm")
    private String typeNm;

    @JsonProperty("enerc")
    private int enerc;

    @JsonProperty("nutConSrtrQua")
    private String nutConSrtrQua;

    @JsonProperty("water")
    private double water;

    @JsonProperty("prot")
    private double prot;

    @JsonProperty("fatce")
    private double fatce;

    @JsonProperty("ash")
    private double ash;

    @JsonProperty("chocdf")
    private double chocdf;

    @JsonProperty("sugar")
    private double sugar;

    @JsonProperty("fibtg")
    private double fibtg;

    @JsonProperty("ca")
    private double ca;

    @JsonProperty("fe")
    private double fe;

    @JsonProperty("p")
    private double p;

    @JsonProperty("k")
    private double k;

    @JsonProperty("nat")
    private double nat;

    @JsonProperty("vitaRae")
    private double vitaRae;

    @JsonProperty("retol")
    private double retol;

    @JsonProperty("cartb")
    private double cartb;

    @JsonProperty("thia")
    private double thia;

    @JsonProperty("ribf")
    private double ribf;

    @JsonProperty("nia")
    private double nia;

    @JsonProperty("vitc")
    private double vitc;

    @JsonProperty("vitd")
    private double vitd;

    @JsonProperty("chole")
    private double chole;

    @JsonProperty("fasat")
    private double fasat;

    @JsonProperty("fatrn")
    private double fatrn;

    @JsonProperty("refuse")
    private int refuse;

    @JsonProperty("srcCd")
    private int srcCd;

    @JsonProperty("srcNm")
    private String srcNm;

    @JsonProperty("foodSize")
    private String foodSize;

    @JsonProperty("imptYn")
    private String imptYn;

    @JsonProperty("cooCd")
    private int cooCd;

    @JsonProperty("cooNm")
    private String cooNm;

    @JsonProperty("companyNm")
    private String companyNm;

    @JsonProperty("dataProdCd")
    private int dataProdCd;

    @JsonProperty("dataProdNm")
    private String dataProdNm;

    @JsonProperty("crtYmd")
    private String crtYmd;

    @JsonProperty("crtrYmd")
    private String crtrYmd;

    @JsonProperty("insttCode")
    private Long insttCode;
}