package board.post.api.service;

import board.post.api.dto.CommentDTO;
import board.post.api.persistence.entity.Comment;
import board.post.api.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 등록
    public void create(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        // 댓글의 게시글 관계 설정 등 필요한 작업 수행
        commentRepository.save(comment);
    }

    // 댓글 수정
    public void update(Long id, CommentDTO commentDTO) {
        // id에 해당하는 댓글 조회
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            // 댓글 내용 수정
            comment.setContent(commentDTO.getContent());
            // 수정된 댓글 저장
            commentRepository.save(comment);
        }
    }

    // 댓글 삭제
    public void delete(Long id) {
        // id에 해당하는 댓글 조회
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            // 댓글 soft delete 처리
            comment.setDeleted(true);
            // soft delete 된 댓글 저장
            commentRepository.save(comment);
        }
    }
}
