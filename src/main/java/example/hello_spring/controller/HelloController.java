package example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";

    }

    @GetMapping("hello-mvc")    // MVC와 템플릿 엔진 예시 -> View와 Controller를 이용
    public String helloMVC(@RequestParam("name") String name, Model model ){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // API 예시 (데이터를 그대로 내림)
    @ResponseBody // HTTP프로토콜(header + body로 구성)의 body에 집어넣겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "안녕 " + name; // "안녕 spring!"
        // 이 경우는 return값이 문자인 경우라 그냥 받아서 넘기면 끝임
    }

    @GetMapping("hello-api") // API를 사용하는 이유를 보여주는 예시
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // ; 까먹었을 시 자동완성 단축키 = ctrl + shift + enter
        Hello hello = new Hello(); // Hello 객체 hello 생성
        hello.setName(name);
        return hello;   // 객체를 return -> default로 json 형식을 따름

    }
    static class Hello{ // class안의 class 선언!
        /* 지나가는 꿀팁!
         객체의 속성 값을 가져오기 : Getter 메서드 -> getName()
         객체의 속성 값을 설정하기 : Setter 메서드 -> setName(String name)
         이를 간편하게 하는 IntelliJ의 단축키 Alt + insert
         */
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // 이처럼 API형식을 사용하면 json형태(key:value)로 출력됨을 알 수 있다.
    }
}
