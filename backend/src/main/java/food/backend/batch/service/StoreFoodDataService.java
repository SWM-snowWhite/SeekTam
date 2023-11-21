package food.backend.batch.service;

import food.backend.batch.dto.FoodNutritionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ID : ST-C-100-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreFoodDataService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * DTO에 담긴 데이터를 DB에 저장한다.
     * UPSERT 쿼리 : INSERT 쿼리를 사용하여 데이터를 삽입하고, 만약 중복된 데이터가 있다면 ON DUPLICATE KEY UPDATE 쿼리를 사용하여 데이터를 업데이트하는 쿼리이다.
     * UPSERT 쿼리를 사용하여 중복된 데이터는 업데이트하고, 새로운 데이터는 삽입한다.
     * 이 때, DB에서 중복을 체크하는 KEY는 반드시 UNIQUE해야 한다.
     * @param foodData DTO에 담긴 데이터
     */
    public void saveFoodDataToDatabase(FoodNutritionDto foodData) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(foodData);
        String sql =
                "INSERT INTO food_main (food_code, food_name, data_code, data_type_name, enerc, nut_con_srtr_qua, water, prot, fatce, ash, chocdf, sugar, fibtg, ca, fe, p, k, nat, vita_rae, retol, cartb, thia, ribf, nia, vitc, vitd, chole, fasat, fatrn, refuse, src_code, src_name, food_size, impt_yn, coo_code, coo_name, company_name, data_prod_code, data_prod_name, crt_ymd, crtr_ymd, instt_code) " +
                        "VALUES (:foodCd, :foodNm, :dataCd, :typeNm, :enerc, :nutConSrtrQua, :water, :prot, :fatce, :ash, :chocdf, :sugar, :fibtg, :ca, :fe, :p, :k, :nat, :vitaRae, :retol, :cartb, :thia, :ribf, :nia, :vitc, :vitd, :chole, :fasat, :fatrn, :refuse, :srcCd, :srcNm, :foodSize, :imptYn, :cooCd, :cooNm, :companyNm, :dataProdCd, :dataProdNm, :crtYmd, :crtrYmd, :insttCode) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "food_name = :foodNm, " +
                        "data_code = :dataCd, " +
                        "data_type_name = :typeNm, " +
                        "enerc = :enerc, " +
                        "nut_con_srtr_qua = :nutConSrtrQua, " +
                        "water = :water, " +
                        "prot = :prot, " +
                        "fatce = :fatce, " +
                        "ash = :ash, " +
                        "chocdf = :chocdf, " +
                        "sugar = :sugar, " +
                        "fibtg = :fibtg, " +
                        "ca = :ca, " +
                        "fe = :fe, " +
                        "p = :p, " +
                        "k = :k, " +
                        "nat = :nat, " +
                        "vita_rae = :vitaRae, " +
                        "retol = :retol, " +
                        "cartb = :cartb, " +
                        "thia = :thia, " +
                        "ribf = :ribf, " +
                        "nia = :nia, " +
                        "vitc = :vitc, " +
                        "vitd = :vitd, " +
                        "chole = :chole, " +
                        "fasat = :fasat, " +
                        "fatrn = :fatrn, " +
                        "refuse = :refuse, " +
                        "src_code = :srcCd, " +
                        "src_name = :srcNm, " +
                        "food_size = :foodSize, " +
                        "impt_yn = :imptYn, " +
                        "coo_code = :cooCd, " +
                        "coo_name = :cooNm, " +
                        "company_name = :companyNm, " +
                        "data_prod_code = :dataProdCd, " +
                        "data_prod_name = :dataProdNm, " +
                        "crt_ymd = :crtYmd, " +
                        "crtr_ymd = :crtrYmd, " +
                        "instt_code = :insttCode ";

        jdbcTemplate.update(sql, param);
    }
}
