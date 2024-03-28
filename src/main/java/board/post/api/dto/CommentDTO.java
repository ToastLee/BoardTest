package board.post.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
//
    private Long id; // 댓글의 고유 식별자
    private String content; // 댓글 내용
}
