package idusw.springboot.boardlgh.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity     // 엔티티 클래스임을 나타내는 어노테이션
@Table(name = "board_a201912016")

@ToString(exclude = "writer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data // = @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor

public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_a201912016_seq_gen")
    @SequenceGenerator(sequenceName = "board_a201912016_seq", name = "board_a201912016_seq_gen", allocationSize = 1)
    private Long bno; // 유일키

    @Column(length = 50, nullable = false)
    private String title; // 제목

    @Column(length = 1000, nullable = false)
    private String content; // 내용

    @ManyToOne
    private MemberEntity writer;  //연관 관계 지정 : 작성자 1명 - 게시물 다수
}
