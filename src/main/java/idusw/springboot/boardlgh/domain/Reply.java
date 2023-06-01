package idusw.springboot.boardlgh.domain;

import java.time.LocalDateTime;

public class Reply {
    private Long rno;
    private String text;
    private String replier;

    private Long bno;   // board와 ManyToOne 관계
    private LocalDateTime reDate;
    private LocalDateTime modDate;
}
