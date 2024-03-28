package board.post.api.persistence.repository;

import board.post.api.persistence.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 삭제되지 않은 게시글 목록 조회 (soft delete)
    Page<Post> findAllByDeletedFalseOrderByCreatedAtDesc(Pageable pageable);
}
