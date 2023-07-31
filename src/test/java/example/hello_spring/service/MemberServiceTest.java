package example.hello_spring.service;

import example.hello_spring.domain.Member;
import example.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach // 각 Test를 실행하기전에 객체를 실행해주는 방식으로 구현
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){    // DB 클리어
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   // Test에서는 한글도 자주 사용
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

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}