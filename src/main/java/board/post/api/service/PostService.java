package board.post.api.service;

import board.post.api.dto.CommentDTO;
import board.post.api.persistence.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import board.post.api.dto.PostDTO;
import board.post.api.persistence.entity.Post;
import board.post.api.persistence.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO create(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Post savedPost = postRepository.save(post);
        return mapToDTO(savedPost);
    }

    public PostDTO update(Long postId, PostDTO postDTO) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            if (!post.isDeleted()) {
                post.setTitle(postDTO.getTitle());
                post.setContent(postDTO.getContent());
                Post updatedPost = postRepository.save(post);
                return mapToDTO(updatedPost);
            } else {
                throw new IllegalArgumentException("Deleted post cannot be updated.");
            }
        } else {
            throw new IllegalArgumentException("Post not found.");
        }
    }


    public Page<PostDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findAllByDeletedFalseOrderByCreatedAtDesc(pageable);

        return postPage.map(this::mapToDTO);
    }

    public PostDTO getById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && !post.isDeleted()) {
            PostDTO postDTO = mapToDTO(post);
            // 댓글 리스트 포함
            List<CommentDTO> commentDTOs = post.getComments().stream()
                    .filter(comment -> !comment.isDeleted()) // 삭제되지 않은 댓글만 포함
                    .map(this::mapToCommentDTO); // Comment를 CommentDTO로 변환하는 메

        }

        private PostDTO mapToDTO (Post post){
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            // 본문은 응답에 포함되지 않으므로 생략
            // 댓글 리스트 역시 생략
            return postDTO;
        }
        // PostService 내에 추가
        public void delete (Long postId){
            Post post = postRepository.findById(postId).orElse(null);
            if (post != null) {
                // 게시글에 연관된 댓글들도 soft delete 처리
                List<Comment> comments = post.getComments();
                for (Comment comment : comments) {
                    comment.setDeleted(true);
                    commentRepository.save(comment);
                }
                // 게시글 soft delete 처리
                post.setDeleted(true);
                postRepository.save(post);
            } else {
                throw new IllegalArgumentException("Post not found.");
            }
        }
    }

    public void delete(Long id) {
    }
}
