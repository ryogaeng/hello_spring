package example.hello_spring.service;   // 회원 서비스를 제공할 service package
                                        // service class의 경우 repository에 비해 비즈니스스러운 워딩을 많이 사용함
import example.hello_spring.domain.Member;
import example.hello_spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 위와 같이 코드를 작성하고 Test코드에서 객체를 생성하면 DB에서 중복되는 에러가 발생할 수 있다.
    // 따라서 다음과 같이 코드를 작성하고 MemberServiceTest에서 @BeforEach를 사용하여 중복을 방지할 수 있다.
    // 이것이 DI(dependency injection)이다.

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름의 중복 회원 X -> validateDuplicateMember 메소드 생성
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);  // memberRepository에 회원 정보를 save
        return member.getId();  // id를 return
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());    // name을 가져와서 result에 저장
        result.ifPresent(m -> { // ifPresent() : null이 아니라 값이 있으면~ 예외 처리(Optional이므로 사용할 수 있는 로직)
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 단일 멤버 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
