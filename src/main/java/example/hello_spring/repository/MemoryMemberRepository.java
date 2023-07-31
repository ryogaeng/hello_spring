package example.hello_spring.repository;

import example.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{  // MemberRepository 인터페이스를 implement한 class MemoryMemberRepository

    private static Map<Long, Member> store = new HashMap<>();  // save 메서드의 저장을 위한 Map store 선언 (key : 회원 id, value는 멤버)
    private static long sequence = 0L;  // key값을 생성 (0,1,2,...) 해주는 sequence 변수 선언

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // 회원 수 하나 늘려주고
        store.put(member.getId(), member);// Map에 회원 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // Map형태인 store에서 id를 key값으로 value값인 member를 get해오기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // store를 loop로 돌면서 name과 동일한게 있으면 return하고, 없으면 Optional를 통해 null return
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // Map의 return은 list형태이므로 값(.values()하면 member를 가져옴)을 가져와서 list에 저장
    }

    public void clearStore(){
        store.clear();
    }
}