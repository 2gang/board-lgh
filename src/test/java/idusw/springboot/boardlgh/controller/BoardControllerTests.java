package idusw.springboot.boardlgh.controller;

import idusw.springboot.boardlgh.domain.Board;
import idusw.springboot.boardlgh.entity.BaseEntity;
import idusw.springboot.boardlgh.entity.BoardEntity;
import idusw.springboot.boardlgh.entity.MemberEntity;
import idusw.springboot.boardlgh.repository.BoardRepository;
import idusw.springboot.boardlgh.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;


@SpringBootTest
@Log4j2
public class BoardControllerTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

//    @Test
//    void createBoard() {
//        Board board = Board.builder()
//                .bno(2L)
//                .title("title2")
//                .content("content2")
//                .writerSeq(1L)
//                .writerEmail("lgh@induk.ac.kr")
//                .writerName("lgh")
//                .build();
//        if(boardService.registerBoard(board) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
//            System.out.println("등록 성공");
//        else
//            System.out.println("등록 실패");
//    }

    @Test
    void createBoard() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 101).forEach(i -> {
            Board board = Board.builder()
                    .bno(Long.valueOf(i))
                    .title("title" + i)
                    .content("content" + i)
                    .writerSeq(1L)
                    .writerEmail("lgh@induk.ac.kr")
                    .writerName("lgh")
                    .boardLike(0L)
                    .build();
            if(boardService.registerBoard(board) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
                System.out.println("등록 성공");
            else
                System.out.println("등록 실패");
        });
    }
}
