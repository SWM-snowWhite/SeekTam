package food.backend.member.dto;


import food.backend.member.Member;
import lombok.AllArgsConstructor;
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

    private String profileImageUrl;
//    private String gender;
//
//    private int birthYear;
//
//    private byte status;
//
//    private int height;
//
//    private int weight;
//
//    private byte activity;
//
//    private byte purposeUse;

    @Builder
    public SignupRequestDto(String email, String applyType, String nickname, String profileImageUrl, String gender, int birthYear,
                            byte status, int height, int weight, byte activity, byte purposeUse) {
        this.email = email;
        this.applyType = applyType;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
//        this.gender = gender;
//        this.birthYear = birthYear;
//        this.status = status;
//        this.height = height;
//        this.weight = weight;
//        this.activity = activity;
//        this.purposeUse = purposeUse;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .applyType(applyType)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
//                .gender(gender)
//                .birthYear(birthYear)
//                .status(status)
//                .height(height)
//                .weight(weight)
//                .activity(activity)
//                .purposeUse(purposeUse)
                .build();
    }
}



