package umc.spring.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.category.domain.Category;
import umc.spring.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class UserPrefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_prefer_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

}
