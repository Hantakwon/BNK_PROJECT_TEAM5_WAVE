package kr.co.wave.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyNoticeDTO {

    private int companyNoticeId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
