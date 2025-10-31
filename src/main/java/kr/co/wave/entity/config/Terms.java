package kr.co.wave.entity.config;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_TERMS")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_ID")
    private int termsId;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name="TYPE")
    private String type; // 유형

    @Column(name = "TITLE")
    private String title; // 제목

    @Column(name = "CONTENT")
    private String content; // 내용

    @Column(name = "VERSION")
    private String version;	// 버전 (예: v1.0, v1.1)

    @Column(name = "IS_REQUIRED")
    private boolean isRequired; // 필수 여부

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDate createdAt; // 생성일

    @Column(name="UPDATED_AT")
    private LocalDate updatedAt; // 수정일
}
