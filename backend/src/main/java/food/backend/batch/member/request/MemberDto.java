package food.backend.batch.member.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private String memberId;
}
