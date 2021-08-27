package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional : java8에 들어간 기능,Null 을 반환하는대신 Opteional을 반환?
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
