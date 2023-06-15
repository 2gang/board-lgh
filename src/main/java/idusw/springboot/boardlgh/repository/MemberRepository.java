package idusw.springboot.boardlgh.repository;

import idusw.springboot.boardlgh.domain.Member;
import idusw.springboot.boardlgh.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, QuerydslPredicateExecutor<MemberEntity> {

    // JpaRepository : 영속 데이터를 처리하기 위해 ORM(Object-Relation_Mapping)을 정의한
    // JPA 사양서를 구현한 구현체에 대한 인터페이스
    // 'org.springframework.boot:spring-boot-starter-data-jpa' : spring-data-jpa 라이브러리에 포힘


    // JPQL
    @Transactional
    @Query("select m from MemberEntity m where m.email = :email and m.pw = :pw")
    MemberEntity getByEmailPw(@Param("email") String email, @Param("pw") String pw);

    MemberEntity findByEmail(String email);

    MemberEntity findByMobile(String mobile);
}
