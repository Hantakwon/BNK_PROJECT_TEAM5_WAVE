package kr.co.wave.repository.card;

import kr.co.wave.entity.card.Account;
import kr.co.wave.entity.card.AnnualFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualFeeRepository extends JpaRepository<AnnualFee, Integer> {


}