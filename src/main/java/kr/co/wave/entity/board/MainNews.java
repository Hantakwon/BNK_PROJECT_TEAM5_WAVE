package kr.co.wave.entity.board;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
// @Table(name = "TB_MAIN_NEWS_BOARD")
public class MainNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NEWS_ID")
    private int newsId;


}
