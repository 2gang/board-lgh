package idusw.springboot.boardlgh.repository;

import idusw.springboot.boardlgh.entity.BoardEntity;
import idusw.springboot.boardlgh.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, SearchBoardRepository {
}
