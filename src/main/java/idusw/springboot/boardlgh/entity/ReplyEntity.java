package idusw.springboot.boardlgh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity     // 엔티티 클래스임을 나타내는 어노테이션
@Table(name = "reply_a201912016")

@ToString(exclude = "board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_a201912016_seq_gen")
    @SequenceGenerator(sequenceName = "reply_a201912016_seq", name = "reply_a201912016_seq_gen", initialValue = 1, allocationSize = 1)
    private Long rno; // 유일키

    private String text;    //댓글 내용
    private String replier; //댓글 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;  //연관 관계 지정 : 작성자 1명 - 게시물 다수
}
