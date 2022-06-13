package com.jojoldu.book.aws_spring_book.web;

import com.jojoldu.book.aws_spring_book.config.auth.dto.SessionUser;
import com.jojoldu.book.aws_spring_book.service.posts.PostsService;
import com.jojoldu.book.aws_spring_book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        // 서버 템플릿 엔진에서 사용 할 수 있는 객체를 저장
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // CustomOAuth2UserService에서 로그인 성공 시 HttpSession 세션에 SessionUser를 저장하도록 구성
        if (user != null) {
            // 세션에 저장된 값이 있을 때만 model에 userName으로 등록
            // 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태. 로그인 버튼이 노출
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
