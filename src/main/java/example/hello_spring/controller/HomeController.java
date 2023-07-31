package example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    // 도메인 첫 화면.. localhost8080으로 들어오면 나타나는 화면
    public String home(){
        return "home";  // home.html 호출
    }
}
