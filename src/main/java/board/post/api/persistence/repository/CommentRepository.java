package board.post.api.persistence.repository;

import board.post.api.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 댓글 등록
    Comment save(Comment comment);

    // 댓글 수정
    // JpaRepository에서 제공하는 save 메서드를 사용하여 댓글 수정이 처리됩니다.

    // 댓글 삭제 (soft delete)
    default void deleteById(Long id) {
        findById(id).ifPresent(comment -> {
            comment.setDeleted(true);
            save(comment);
        });
    }
}
