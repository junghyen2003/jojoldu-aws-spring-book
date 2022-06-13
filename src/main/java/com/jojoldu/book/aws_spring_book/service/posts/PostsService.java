package com.jojoldu.book.aws_spring_book.service.posts;

import com.jojoldu.book.aws_spring_book.domain.posts.Posts;
import com.jojoldu.book.aws_spring_book.domain.posts.PostsRepository;
import com.jojoldu.book.aws_spring_book.web.dto.PostsResponseDto;
import com.jojoldu.book.aws_spring_book.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.aws_spring_book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 데이터베이스에 쿼리를 날리는 부분이 없는 이유는 JPA의 영속성 컨텍스트 때문
        // 영속성 컨텍스트란 엔티티를 영구 저장하는 환경
        // JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림
        // JPA의 엔티티 매니저가 활성화 된 상태로 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 해당 데이터는 영속성 컨텍스트가 유지 된 상태
        // 유지된 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        // Entity 객체의 값을 변경하면 별도로 Update 쿼리를 날리 필요가 없다는 것
        // 이 개념을 "더티 체킹" 이라고 함
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
