package com.jojoldu.book.chapter1.dto;

import com.jojoldu.book.chapter1.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertj 라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받음
        // 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용 할 수 있음
        // assertj 의 동등 비교 메소드
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공

        // JUnit의 assertThat이 아닌 assertj의 assertThat을 사용하였음
        // JUnit과 비교했을때 assertj의 장점은 다음과 같음
        // CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
            // JUnit의 assertThat을 사용하면 is()와 같이 CoreMatchers 라이브러리가 필요
        // 자동완성이 좀 더 확실하게 지원
    }
}
