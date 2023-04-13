package idusw.springboot.boardlgh.repository;

import idusw.springboot.boardlgh.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

}
