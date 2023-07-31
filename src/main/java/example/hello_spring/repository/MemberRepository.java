package example.hello_spring.repository;

import example.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;  // java8의 기능.. findById, findByName으로 id를 찾았는데, 없는 경우 null이 반환되게끔 해주는 Optional..

public interface MemberRepository {
    Member save(Member member); // 저장소에 회원 저장하기
    Optional<Member> findById(Long id); // id로 회원 찾기
    Optional<Member> findByName(String name); // name으로 회원 찾기
    List<Member> findAll(); // 저장소에 저장된 모든 회원 리스트 반환
}
