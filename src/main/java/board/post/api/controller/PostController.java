package board.post.api.controller;

import board.post.api.dto.PostDTO;
import board.post.api.service.PostService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts") // 주소 구조를 좀 더 직관적으로 변경
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
//
    // 게시글 등록
    @PostMapping
    public void create(@RequestBody PostDTO postDTO) {
        postService.create(postDTO);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        postService.update(id, postDTO);
    }

    // 게시글 목록 조회
    @GetMapping
    public List<PostDTO> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        return (List<PostDTO>) postService.getAll(page, size);
    }


    // 게시글 단 건 조회
    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable Long id) {
        return postService.getById(id);
    }
}
