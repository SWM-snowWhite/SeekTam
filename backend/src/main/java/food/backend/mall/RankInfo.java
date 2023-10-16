package food.backend.mall;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.Date;

@Getter
@RedisHash(value="rankInfo")
@Data
@NoArgsConstructor
public class RankInfo {
    @Id
    private Long id;
    private Date createdDate;
    private Integer ranking;
    private String foodKeyword;
    private Integer hits;
}
