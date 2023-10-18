package food.backend.search.model;

import food.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ViewsRanking extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int ranking;

    @Column(nullable = false)
    private String foodKeyword;

    @Column(nullable = false)
    private int calories;

    @Column(nullable = false)
    private boolean liked;

    @Builder
    public ViewsRanking(int ranking, String foodKeyword, int calories, boolean liked) {
        this.ranking = ranking;
        this.foodKeyword = foodKeyword;
        this.calories = calories;
        this.liked = liked;
    }
}
