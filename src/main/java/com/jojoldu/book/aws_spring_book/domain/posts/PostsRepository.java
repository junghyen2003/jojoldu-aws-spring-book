package com.jojoldu.book.aws_spring_book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// ibatis, MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// Entity 클래스와 기본 Entity Repository는 함께 위치 해야 함
// 둘은 아주 밀접한 관계이고 Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용
    // 대표적인 예로 querydsl, jooq, MyBatis 등이 있음
    // 조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 통해 진행. 필자는 개인적으로 querydsl를 추천
    // 1. 타입 안정성이 보장됨
    // 2. 국내 많은 회사에서 사용
    // 3. 레퍼런스가 많이 존재
    List<Posts> findAllDesc();
}
