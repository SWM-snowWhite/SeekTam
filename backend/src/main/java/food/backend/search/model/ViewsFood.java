package food.backend.search.model;

import food.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ViewsFood extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long foodId;

    @Column(nullable = false)
    private Long memberId;

    @Builder
    public ViewsFood(Long foodId, Long memberId) {
        this.foodId = foodId;
        this.memberId = memberId;
    }
}
