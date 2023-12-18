package food.backend.member.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Slf4j
@Repository
public class MemberDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void withdrawalByEmail(String email) {
        log.info("email ", email);
        String sql = "UPDATE member SET use_yn = 0 WHERE email = :email";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(email, email);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
