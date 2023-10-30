package food.backend.member.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private String memberId;
}
