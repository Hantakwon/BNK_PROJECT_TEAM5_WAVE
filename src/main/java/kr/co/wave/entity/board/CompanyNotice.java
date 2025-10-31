package kr.co.wave.entity.board;

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
@Table(name = "TB_COMPANY_NOTICE_BOARD")
public class CompanyNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPANY_NOTICE_ID")
        private int companyNoticeId;

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
