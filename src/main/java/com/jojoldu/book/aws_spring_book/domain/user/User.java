package com.jojoldu.book.aws_spring_book.domain.user;

import com.jojoldu.book.aws_spring_book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    // JPA로 데이터베이스에 저장 할 때 Enum 값을 어떤 형태로 저장 할 지 결정
    // 기본적으로는 int로 된 숫자. 숫자로 저장 되면 데이터베이스로 확인 할 때 무슨 코드를 의미하는지 알 수 없음.
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
