package kr.co.wave.repository.board.company;

import kr.co.wave.dto.board.company.InvestorInfoDTO;
import kr.co.wave.entity.board.company.InvestorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestorInfoRepository extends JpaRepository<InvestorInfo, Integer> {

    // 은행 소개 - 투자자정보
    /*
    @Query(
            value = """
                      select new kr.co.wave.dto.board.company.InvestorInfoDTO(
                         i.investorInfoId, i.title, i.content, i.writer, i.createdAt
                      )
                      from InvestorInfo i
                      where
                          (:keyword is null or :keyword = '')
                          or (
                            ( :searchType is null or :searchType = '' ) and (
                              lower(i.title)    like lower(concat('%', :keyword, '%')) or
                              lower(cast(i.content as string))  like lower(concat('%', :keyword, '%')) or
                              lower(i.writer) like lower(concat('%', :keyword, '%'))
                            )
                          )
                          or (:searchType = 'title'    and lower(i.title)    like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'content'  and lower(cast(i.content as string))  like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'writer' and lower(i.writer) like lower(concat('%', :keyword, '%')))
                    """
    )
    Page<InvestorInfoDTO> findInvestorInfoBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);
    */
    @Query(
            value = """
                  SELECT i.investorinfo_id, i.title, i.content, i.writer, i.created_at
                  FROM TB_COMPANY_INVESTORINFO i
                  WHERE
                      (:keyword is null or :keyword = '')
                      or (
                        ( :searchType is null or :searchType = '' ) and (
                          lower(n.title) like lower('%' || :keyword || '%') or
                          DBMS_LOB.INSTR(lower(n.content), lower(:keyword), 1, 1) > 0 or
                          lower(n.writer) like lower('%' || :keyword || '%')
                        )
                      )
                      or (:searchType = 'title' and lower(n.title) like lower('%' || :keyword || '%'))
                      or (:searchType = 'content' and DBMS_LOB.INSTR(lower(n.content), lower(:keyword), 1, 1) > 0)
                      or (:searchType = 'writer' and lower(n.writer) like lower('%' || :keyword || '%'))
                """,
            nativeQuery = true
    )
    Page<Object[]> findInvestorInfoBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);

    // 최신글 5개 찾아오기
    List<InvestorInfo> findTop5ByOrderByCreatedAtDesc();
}