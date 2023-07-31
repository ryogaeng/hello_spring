package example.hello_spring.service;

import example.hello_spring.repository.JdbcTemplateMemberRepository;
import example.hello_spring.repository.JpaMemberRepository;
import example.hello_spring.repository.MemberRepository;
import example.hello_spring.repository.JdbcMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * java 코드로 직접 spring bin에 등록하기
 */

@Configuration  // Configuration annotation으로 spring이 자동으로 올리도록
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){   // MemberService 올리기
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){ // MemberRepository는 인터페이스고 / MemberRepository 올리기
       // return new MemoryMemberRepository(); // MemoryMemberRepository()가 구현체니까!
        // return new JdbcMemberRepository(dataSource);
      //  return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
