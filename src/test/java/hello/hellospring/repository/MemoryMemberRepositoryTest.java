package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 테스트를 먼저 만들고 구현 클래스를 만드는 과정(미리 틀을만들어보는 것) 테스트 주도 개발, tdd 라고한다.
// 다른곳에 가져다 쓸필요가 없기때문에 public 대신 default 사용
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드가 끝날때마다 어떠한 동작을 하게하는 Annotation (call back method)
    @AfterEach
    public void afterEach(){
        repository.clearStore();
        // 메서드가 끝날때마다 repository를 한번씩 지워준다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional
//        System.out.println("result =" + (result == member));
//        Assertions.assertEquals(member,result);
        /*위처럼 println을 이용할수도 있지만, 글자로 계속 볼수는 없다.
        이럴때 사용하는 기능이 junit 의 assertions 이다*/
        assertThat(member).isEqualTo(result);
        // 간편한 assertJ의 assertions의 기능을 자주사용한다.
        // alt + Enter 를사용하여 static import를 하여 Assertions 생략 가능?
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // spring1 과 spring2 회원이 가입된 상태

        //findByName이 잘동작하는지 테스트
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

     /*위와같이 메소드를 작성후 전체 테스트케이스를 돌려보면 findByName()만 에러가 나는것을 확인 할 수가 있다.
    모든 테스트 순서는 보장이 되지 않는다. 모든 테스트는 순서랑 상관없이 메서드별로 따로 동작할 수 있도록 설계를 해야한다.
    그러므로 테스트가 끝나고나면 데이터를 클리어 해줘야한다.*/
}
