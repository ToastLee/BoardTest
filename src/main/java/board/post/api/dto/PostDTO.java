package board.post.api.dto;

import java.util.List;

public class PostDTO {
//
    private Long id; // 게시글의 고유 식별자
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private List<CommentDTO> comments; // 게시글에 등록된 댓글 목록

    public void setTitle(String title) {
    }

    public void setId(Long id) {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    // 생성자, getter, setter 등 필요한 메서드를 추가할 수 있습니다.

}
