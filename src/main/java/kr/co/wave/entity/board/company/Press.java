package kr.co.wave.entity.board.company;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_COMPANY_PRESS")
public class Press {

    // 은행 소개 - 보도 자료
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRESS_ID")
    private int pressId;

    @Column(name="TITLE")
    private String title;

    @Lob // CLOB으로 변경
    @Column(name="CONTENT")
    private String content;

    @Column(name="WRITER")
    private String writer;

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;
}
