package idusw.springboot.boardlgh.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import idusw.springboot.boardlgh.domain.Member;
import idusw.springboot.boardlgh.domain.Memo;
import idusw.springboot.boardlgh.domain.PageRequestDTO;
import idusw.springboot.boardlgh.domain.PageResultDTO;
import idusw.springboot.boardlgh.entity.MemberEntity;
import idusw.springboot.boardlgh.entity.MemoEntity;
import idusw.springboot.boardlgh.entity.QMemberEntity;
import idusw.springboot.boardlgh.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class MemberServiceImpl implements MemberService{
    MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member m) {
        // DTO -> Entity : Repository에서 처리하기 위해
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null) { // 저장 성공
            return 1;
        } else
            return 0;
    }

    @Override
    public Member read(Member m) {
        MemberEntity e = memberRepository.getById(m.getSeq());
        Member member = Member.builder()
                .seq(e.getSeq())
                .email(e.getEmail())
                .name(e.getName())
                .pw(e.getPw())
                .build();
        return member;
    }

    @Override
    public List<Member> readList() {
        // 1.매개변수를 처리하고(처리를 위한 객체 선언 및 초기화), 리파지터리 객체에게 전달
        // 2.리파지터리 객체의 해당 메소드가 처리한 결과를 반환 유형으로 변환
        // repository : entity를 대상으로 함, service : dto or domain 객체를 대상으로 함

        List<Member> result = new ArrayList<>(); // Memo 객체를 원소로 갖는 리스트형 객체를 생성, 배정
        List<MemberEntity> entities = memberRepository.findAll(); // select * from a_memo;
        for(MemberEntity e : entities) {
            // Functional Language 특징을 활용한 처리, 1.8 Lambda 식 -> Lombok Library 사용
            Member m = Member.builder()
                    .seq(e.getSeq())
                    .email(e.getEmail())
                    .name(e.getName())
                    .pw(e.getPw())
                    .regDate(e.getRegDate())
                    .modDate(e.getModDate())
                    .build();   // DTO(Data Transfer Object) : Controller - Service or Controller - View
            result.add(m);
        }
        return result;
    }

    @Override
    public int update(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null) // 저장 성공
            return 1;
        else
            return 0;
    }

    @Override
    public int delete(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .build();
        memberRepository.deleteById(entity.getSeq());
        return 1;
    }

    @Override
    public Member login(Member m) { // 인터페이스에 선언한 선안된 메소드 중 구현하지 않는 메소드를 구혀해야 함.
        MemberEntity e = memberRepository.getByEmailPw(m.getEmail(), m.getPw()); // JpaRepository 구현체의 메소드
        System.out.println("login : " + e);
        Member result = null; // DTO (Data Transfer Object) : Controller - Service or Controller - View
        if(e != null) {
            result = new Member();
            result.setSeq(e.getSeq());
            result.setEmail(e.getEmail());
            result.setName(e.getName());
        }
        return result;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO) {
//        Pageable pageable = requestDTO.getPageable(Sort.by("seq").ascending());
        Sort sort = Sort.by("seq").descending();
//        if(requestDTO.getSort() == null) {
//            sort = Sort.by("seq").descending();
//        }
//        else {
//            sort = Sort.by("seq").ascending();
//        }

//        Page<MemberEntity> result = memberRepository.findAll(pageable);

        Pageable pageable = requestDTO.getPageable(sort);

        BooleanBuilder booleanBuilder = findByCondition(requestDTO);
//        Page<MemberEntity> result = memberRepository.findAll(pageable);
        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);


        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));

        PageResultDTO pageResultDTO = new PageResultDTO<>(result, fn, requestDTO.getPerPagination());

        return pageResultDTO;
    }

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

        BooleanExpression expression = qMemberEntity.seq.gt(0L); // where seq > 0 and title == "title"
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) { // email로 검색
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        }
        if(type.contains("n")) { // name로 검색
            conditionBuilder.or(qMemberEntity.name.contains(keyword));
        }
//        if(type.contains("p")) { // phone로 검색
//            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
//        }
//        if(type.contains("a")) { // address로 검색
//            conditionBuilder.or(qMemberEntity.address.contains(keyword));
//        } // 조건을 전부 줄 수도 있으니 if else문 아님
//        if(type.contains("l")) {
//            conditionBuilder.or(qMemberEntity.level.contains(keyword));
//        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }


}
