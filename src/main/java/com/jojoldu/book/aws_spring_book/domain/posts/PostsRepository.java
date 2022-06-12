package com.jojoldu.book.aws_spring_book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// ibatis, MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// Entity 클래스와 기본 Entity Repository는 함께 위치 해야 함
// 둘은 아주 밀접한 관계이고 Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
