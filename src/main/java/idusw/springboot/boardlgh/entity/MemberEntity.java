package idusw.springboot.boardlgh.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티 클래스임으로 나타내는 애노테이션
@Table(name = "a201912016_member")

@ToString   // lombok 라이브러리 사용
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class MemberEntity extends BaseEntity{
    // Entity : Service -> Repository -> Service 데이터 객체, DataBase 관점
    // Repository : Persistence Data 처리
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a201912016_member_seq_gen")
    @SequenceGenerator(sequenceName = "a201912016_member_seq", name="a201912016_member_seq_gen",
            initialValue = 1, allocationSize = 1)
    // Oracle : GenerationType.SEQUENCE, Mysql : GenerationType.IDENTITY
    // Mysql : auto increment, Oracle : sequence - 자동 증가 식별번호
    private Long seq;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 13, nullable = false)
    private String mobile;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String pw;

    @Column(length = 5, nullable = false)
    private String zipcode;

    @Column(name = "abandon")
    private boolean abandon = false;

    public void setAbandon(boolean abandon) {
        this.abandon = abandon;
    }
}
