package com.jojoldu.book.aws_spring_book.config.auth.dto;

import com.jojoldu.book.aws_spring_book.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // User 클래스에 직렬화 코드를 넣지 않고 직렬화 기능을 가진 세션 Dto를 추가로 만듦
    // User 클래스가 엔티티 이기 때문에 언제 다른 엔티티와 관계가 형성 될지 알 수 없음
    // 예를 들어 @OneToMany, @ManyToMany 등 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함되어 성능 이슈, 부수 효과가 발생 할 확률이 높음
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
