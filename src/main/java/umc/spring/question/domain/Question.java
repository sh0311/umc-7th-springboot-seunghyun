package umc.spring.question.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="question", cascade=CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<QuestionImage> images=new ArrayList<QuestionImage>();



    //연관관계 편의 메소드 (이미지는 하나의 문의사항에 대해서도 여러 이미지 엔티티의 추가와 삭제가 빈번하게 일어나서 add, remove 두개로 나누어 연관관계를 관리하는 게 좋다)

    public void addImage(QuestionImage image) {
        images.add(image);
        if(image.getQuestion()!=this)
            image.setQuestion(this);
    }

    public void removeImage(QuestionImage image) {
        images.remove(image);
        image.removeQuestion();
    }

}
