package example.hello_spring.repository;

import example.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {  // 다른데서 가져다 쓸게 아니기 때문에 public은 생략!

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 각 Test를 거치고나면 실행되는..
    public void afterEach(){
        repository.clearStore();    // repository를 비울 것 임
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Ryogaeng");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // member Ryogaeng이 expect하는 result와 같은지 여부를 판단
    }

    @Test
    public void  findByName(){
        Member member1 = new Member();
        member1.setName("Ryogaeng1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Ryogaeng2");
        repository.save(member2);

        Member result = repository.findByName("Ryogaeng2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Ryogaeng1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Ryogaeng2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
