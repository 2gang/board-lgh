package idusw.springboot.boardlgh.service;

import idusw.springboot.boardlgh.domain.Member;
import idusw.springboot.boardlgh.domain.PageRequestDTO;
import idusw.springboot.boardlgh.domain.PageResultDTO;
import idusw.springboot.boardlgh.entity.MemberEntity;

import java.util.List;

public interface MemberService{
    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    Member login(Member m);

    PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO);

    // default : java 1.8 버전부터 인터페이스가 기본 메서드를 가질 수 있도록 함
    default MemberEntity dtoToEntity(Member dto) {  // dto객체를 entity 객체로 변환 : service -> repository
        MemberEntity entity = MemberEntity.builder()
                .seq((dto.getSeq()))
                .email(dto.getEmail())
                .name(dto.getName())
                .pw(dto.getPw())
                .build();
        return entity;
    }

    default Member entityToDto(MemberEntity entity) {
        Member dto = Member.builder()
                .seq(entity.getSeq())
                .email(entity.getEmail())
                .name(entity.getName())
                .pw(entity.getPw())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
