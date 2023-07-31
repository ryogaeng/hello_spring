package example.hello_spring.service;

import example.hello_spring.domain.Member;
import example.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // test시작 전에 transactional을 걸고, DB에 query다 날리고, test가 끝나면 그 데이터를 자동으로 롤백해줌
    // 그 말인 즉슨 굳이 지우는 코드를 넣지 않아도, 다음 테스트를 진행하는데 순조롭게 해줌
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test void 회원가입() {   // Test에서는 한글도 자주 사용
        // given : ~가 주어졌을 때
        Member member = new Member();
        member.setName("ryogaeng1");

        // when : ~를 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 ~ 하다 ~
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외_예시(){
        // given
        Member member1 = new Member();
        member1.setName("ryogaeng1");

        Member member2 = new Member();
        member2.setName("ryogaeng1");

        // when
        /**
         * 1번
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        /**
         * 2번
         */
         memberService.join(member1);
        IllegalStateException  e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}