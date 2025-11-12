package kr.co.wave.repository.card;

import kr.co.wave.entity.card.Account;
import kr.co.wave.entity.card.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, Integer> {

}