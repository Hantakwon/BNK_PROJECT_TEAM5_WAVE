package kr.co.wave.entity.board.cs;

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
@Table(name = "TB_DISCLOSURE")
public class Disclosure {

    // 상품공시실
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISCLOSURE_ID")
    private int disclosureId;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="WRITER")
    private String writer;

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;
}
