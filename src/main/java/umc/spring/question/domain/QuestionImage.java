package umc.spring.question.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class QuestionImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_image_id")
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;


    //연관관계 편의 메소드
    public void mapQuestion(Question question) {
        this.question = question;
        question.getImages().add(this);
    }
}
