package example.hello_spring.controller;

import example.hello_spring.domain.Member;
import example.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // Controller의 경우 Configuration -> Bean으로 등록 불가능.. 따라서 Autowired를 선언해서 올려준다
    public  MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        // member 생성
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("Member = " + member.getName());

        // 회원가입 시의 memberService.join
        memberService.join(member);

        return "redirect:/";
    }

    // 회원 목록 조회
    @GetMapping("/members")
    public String list(Model model){
        // list로 담자
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
