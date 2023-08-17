package food.backend.batch.service;

import food.backend.batch.dto.FoodNutritionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreFoodDataService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void saveFoodDataToDatabase(FoodNutritionDto foodData) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(foodData);
        String sql =
                "INSERT INTO batch_test (food_code, food_name, data_code, data_type_name, enerc, nut_con_srtr_qua, water, prot, fatce, ash, chocdf, sugar, fibtg, ca, fe, p, k, nat, vita_rae, retol, cartb, thia, ribf, nia, vitc, vitd, chole, fasat, fatrn, refuse, src_code, src_name, food_size, impt_yn, coo_code, coo_name, company_name, data_prod_code, data_prod_name, crt_ymd, crtr_ymd, instt_code, update_date) " +
                        "VALUES (:foodCd, :foodNm, :dataCd, :typeNm, :enerc, :nutConSrtrQua, :water, :prot, :fatce, :ash, :chocdf, :sugar, :fibtg, :ca, :fe, :p, :k, :nat, :vitaRae, :retol, :cartb, :thia, :ribf, :nia, :vitc, :vitd, :chole, :fasat, :fatrn, :refuse, :srcCd, :srcNm, :foodSize, :imptYn, :cooCd, :cooNm, :companyNm, :dataProdCd, :dataProdNm, :crtYmd, :crtrYmd, :insttCode, :updateDate) " +
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
                        "instt_code = :insttCode, " +
                        "update_date = :updateDate";

        jdbcTemplate.update(sql, param);
    }
}
