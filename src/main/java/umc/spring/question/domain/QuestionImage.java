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


    void setQuestion(Question question) {
        this.question = question;
    }

    public void removeQuestion(){
        this.question = null;
    }
}
