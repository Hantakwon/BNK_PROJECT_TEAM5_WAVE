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
// @Table(name = "TB_MAIN_ETC_BOARD")
public class MainEtc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ETC_ID")
    private int etcId;
}
