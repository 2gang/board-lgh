package idusw.springboot.boardlgh.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@EqualsAndHashCode

public class Reply {
    private Long rno;
    private String text;
    private String replier;

    private Long bno;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
