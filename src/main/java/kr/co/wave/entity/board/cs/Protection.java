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
@Table(name = "TB_PROTECTION")
public class Protection {

    // 금융소비자보호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROTECTION_ID")
    private int protectionId;

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
