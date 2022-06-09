package com.jojoldu.book.aws_spring_book.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션 일 수록 클래스에 가깝게 두자! ( JPA > Lombok ). 이후 새 언어 전환으로 lombok이 필요 없어진 경우 쉽게 삭제가 가능하다
@Getter
@NoArgsConstructor
@Entity
// 테이블과 링크 될 클래스임을 나타냄
// 기본 값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
public class Posts {
    @Id
    // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK 생성 규칙
    // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false)
    // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼
    // 사용하는 이유는 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
