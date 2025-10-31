package kr.co.wave.repository;

import kr.co.wave.entity.board.CompanyNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyNoticeRepository extends JpaRepository<CompanyNotice, Integer> {

}