package idusw.springboot.boardlgh.service;

import idusw.springboot.boardlgh.domain.Board;
import idusw.springboot.boardlgh.domain.PageRequestDTO;
import idusw.springboot.boardlgh.domain.PageResultDTO;
import idusw.springboot.boardlgh.entity.BoardEntity;
import idusw.springboot.boardlgh.entity.MemberEntity;
import idusw.springboot.boardlgh.repository.BoardRepository;
import idusw.springboot.boardlgh.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

//    public BoardServiceImpl(BoardRepository boardRepository, ReplyRepository replyRepository) {
//        this.boardRepository = boardRepository;
//        this.replyRepository = replyRepository;
//    }

    @Override
    public int registerBoard(Board dto) {
        BoardEntity entity = dtoToEntity(dto);

        if(boardRepository.save(entity) != null) { // 저장 성공
            return 1;
        } else
            return 0;
    }

    @Override
    public Board findBoardById(Board board) {
        Object[] entities = (Object[]) boardRepository.getBoardByBno(board.getBno());
        return entityToDto((BoardEntity) entities[0], (MemberEntity) entities[1], (Long) entities[2]);
    }

    @Override
    public PageResultDTO<Board, Object[]> findBoardAll(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending()));
        Function<Object[], Board> fn = (entity -> entityToDto((BoardEntity) entity[0],
                (MemberEntity) entity[1], (Long) entity[2]));
        return new PageResultDTO<>(result, fn,5);
    }

    @Override
    public int updateBoard(Board board) {
        BoardEntity entity = BoardEntity.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
        if (boardRepository.save(entity) != null) { // 저장 성공
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int deleteBoard(Board board) {
        replyRepository.deleteByBno(board.getBno());
        boardRepository.deleteById(board.getBno());
        return 0;
    }
}
