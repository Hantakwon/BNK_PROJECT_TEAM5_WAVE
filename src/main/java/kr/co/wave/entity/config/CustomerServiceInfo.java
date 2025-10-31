package kr.co.wave.entity.config;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_CUSTOMER_SERVICE_INFO")
public class CustomerServiceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CS_ID")
    private int csId;

    // 고객센터 정보
    @Column(name="CUSTOMER_PHONE")
    private String customerPhone;        // 전화번호

    @Column(name="CUSTOMER_HOURS")
    private String customerHours;        // 업무시간

    @Column(name="CUSTOMER_EMAIL")
    private String customerEmail;        // 이메일

    @Column(name="DISPUTE_CONTACT")
    private String disputeContact;       // 전자금융거래 분쟁담당
}