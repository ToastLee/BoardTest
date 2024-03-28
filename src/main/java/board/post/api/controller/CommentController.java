package board.post.api.controller;

import board.post.api.dto.CommentDTO;
import board.post.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public void create(@RequestBody CommentDTO commentDTO) {
        commentService.create(commentDTO);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        commentService.update(id, commentDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}
