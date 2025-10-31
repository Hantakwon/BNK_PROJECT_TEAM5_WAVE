package kr.co.wave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_MEMBER")
public class Member {

    @Id
    @Column(name = "mem_id")
    private String memId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @Column(name = "role")
    private String role = "GENERAL";

}
