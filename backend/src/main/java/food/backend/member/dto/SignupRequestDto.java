package food.backend.member.dto;


import food.backend.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String email;

    private String applyType;

    private String nickname;

    private String gender;

    private int birthYear;

    private byte status;

    private int height;

    private int weight;

    private byte activity;

    private byte purposeUse;

    private LocalDateTime lastAccessDate;

    @Builder
    public SignupRequestDto(String email, String applyType, String nickname, String gender, int birthYear,
                            byte status, int height, int weight, byte activity, byte purposeUse,
                            LocalDateTime lastAccessDate) {
        this.email = email;
        this.applyType = applyType;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.status = status;
        this.height = height;
        this.weight = weight;
        this.activity = activity;
        this.purposeUse = purposeUse;
        this.lastAccessDate = lastAccessDate;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .applyType(applyType)
                .nickname(nickname)
                .gender(gender)
                .birthYear(birthYear)
                .status(status)
                .height(height)
                .weight(weight)
                .activity(activity)
                .purposeUse(purposeUse)
                .lastAccessDate(lastAccessDate)
                .build();
    }
}



