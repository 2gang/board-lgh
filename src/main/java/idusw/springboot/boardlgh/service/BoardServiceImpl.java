package idusw.springboot.boardlgh.service;

import idusw.springboot.boardlgh.domain.Board;
import idusw.springboot.boardlgh.domain.PageRequestDTO;
import idusw.springboot.boardlgh.domain.PageResultDTO;
import idusw.springboot.boardlgh.entity.BoardEntity;
import idusw.springboot.boardlgh.entity.MemberEntity;
import idusw.springboot.boardlgh.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class BoardServiceImpl implements BoardService{
    private BoardRepository boardRepository;
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

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
        return null;
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
        return 0;
    }

    @Override
    public int deleteBoard(Board board) {
        return 0;
    }
}
