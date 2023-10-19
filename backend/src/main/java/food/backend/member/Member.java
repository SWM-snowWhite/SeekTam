package food.backend.member;

import food.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "apply_type", nullable = false, length = 2)
    private String applyType;

    @Column(name = "nickname", nullable = false, length = 255)
    private String nickname;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Column(name = "status", nullable = false)
    private byte status;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "activity", nullable = false)
    private byte activity;

    @Column(name = "purpose_use", nullable = false)
    private byte purposeUse;

    @Column(name = "last_access_date")
    private LocalDateTime lastAccessDate;

    @Builder
    public Member(Long id, String email, String applyType, String nickname, String gender, Integer birthYear,
                  byte status, int height, int weight, byte activity, byte purposeUse,
                  LocalDateTime lastAccessDate) {
        this.id = id;
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
}
