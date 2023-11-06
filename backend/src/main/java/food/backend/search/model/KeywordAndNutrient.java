package food.backend.search.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * ID : ST-C-110-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 *
 * @deprecated
 */
@Deprecated
@Data
public class KeywordAndNutrient {

    /**
     * keyword 식품명 검색에 사용되는 키워드
     */
    @NotNull
    @NotBlank
    private String keyword;

    /**
     * enerc 열량(kcal) 입력값
     */
    private String enerc;

    /**
     * enercCon 열량(kcal) 조건, 1이면 이상, 0이면 이하
     */
    private String enercCon;

    /**
     * chocdf 탄수화물(g) 입력값
     */
    private String chocdf;

    /**
     * chocdfCon 탄수화물(g) 조건, 1이면 이상, 0이면 이하
     */
    private String chocdfCon;

    /**
     * prot 단백질(g) 입력값
     */
    private String prot;

    /**
     * protCon 단백질(g) 조건, 1이면 이상, 0이면 이하
     */
    private String protCon;

    /**
     * fatce 지방(g) 입력값
     */
    private String fatce;

    /**
     * fatceCon 지방(g) 조건, 1이면 이상, 0이면 이하
     */
    private String fatceCon;

    /**
     * 요청파라미터명과 일치하는 필드에 값을 할당하는 생성자<br> 이외의 필드는 요청파라미터명과 필드명이 일치하기 때문에 setter를 통해 값을 할당받음
     *
     * @param keyword    요청파라미터의 keyword값을 keyword 필드에 할당
     * @param enerc_con  요청파라미터의 enerc_con값을 enercCon 필드에 할당
     * @param chocdf_con 요청파라미터의 chocdf_con값을 chocdfCon 필드에 할당
     * @param prot_con   요청파라미터의 prot_con값을 protCon 필드에 할당
     * @param fatce_con  요청파라미터의 fatce_con값을 fatceCon 필드에 할당
     */

    @Builder
    public KeywordAndNutrient(String keyword, String enerc_con, String chocdf_con, String prot_con, String fatce_con) {
        this.keyword = keyword;
        this.enercCon = enerc_con;
        this.chocdfCon = chocdf_con;
        this.protCon = prot_con;
        this.fatceCon = fatce_con;
    }
}
