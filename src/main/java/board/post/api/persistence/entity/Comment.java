package board.post.api.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private boolean deleted = false; // soft delete를 위한 플래그

    private LocalDateTime createdAt;

    // 생성자, Getter, Setter 등 필요한 메서드를 추가할 수 있습니다.
}
