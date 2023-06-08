package idusw.springboot.boardlgh.service;


import idusw.springboot.boardlgh.domain.Reply;
import idusw.springboot.boardlgh.entity.BoardEntity;
import idusw.springboot.boardlgh.entity.ReplyEntity;

import java.util.List;

public interface ReplyService {
    Long register(Reply dto);

    List<Reply> getList(Long bno);

    void modify(Reply dto);

    void remove(Reply dto);

    default ReplyEntity dtoToEntity(Reply dto) {
        BoardEntity board = BoardEntity.builder().bno(dto.getBno()).build();
        return ReplyEntity.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replier(dto.getReplier())
                .board(board)
                .build();
    }

    default Reply entityToDto(ReplyEntity entity) {
        return Reply.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replier(entity.getReplier())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
