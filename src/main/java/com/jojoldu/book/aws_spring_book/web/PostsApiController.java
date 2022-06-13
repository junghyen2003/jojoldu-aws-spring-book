package com.jojoldu.book.aws_spring_book.web;

import com.jojoldu.book.aws_spring_book.service.posts.PostsService;
import com.jojoldu.book.aws_spring_book.web.dto.PostsResponseDto;
import com.jojoldu.book.aws_spring_book.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.aws_spring_book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestsDto) {
        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
