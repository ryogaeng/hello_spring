package example.hello_spring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // id 식별자가 있어야한다(고객이 정의내리는 id가 아니라 시스템이 정의하는 id임)

    // DB에 있는 "name"을 "username"으로 바꾸고 싶으면 @Column사용
    // @Column(name = "username")
    public String name; // 이름이 있어야한다

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
