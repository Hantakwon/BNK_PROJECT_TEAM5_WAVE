package kr.co.wave.repository.board.company;

import kr.co.wave.dto.board.company.PressDTO;
import kr.co.wave.entity.board.company.Press;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PressRepository extends JpaRepository<Press, Integer> {

    // 은행 소개 - 보도자료
    /*
    @Query(
            value = """
                      select new kr.co.wave.dto.board.company.PressDTO(
                         p.pressId, p.title, p.content, p.writer, p.createdAt
                      )
                      from Press p
                      where
                          (:keyword is null or :keyword = '')
                          or (
                            ( :searchType is null or :searchType = '' ) and (
                              lower(p.title)    like lower(concat('%', :keyword, '%')) or
                              lower(cast(p.content as string))  like lower(concat('%', :keyword, '%')) or
                              lower(p.writer) like lower(concat('%', :keyword, '%'))
                            )
                          )
                          or (:searchType = 'title'    and lower(p.title)    like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'content'  and lower(cast(p.content as string))  like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'writer' and lower(p.writer) like lower(concat('%', :keyword, '%')))
                    """
    )
    Page<PressDTO> findPressBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);
    */
    @Query(
            value = """
                  SELECT p.press_id, p.title, p.content, p.writer, p.created_at
                  FROM TB_COMPANY_PRESS p
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
    Page<Object[]> findPressBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);

    // 최신글 5개 찾아오기
    List<Press> findTop5ByOrderByCreatedAtDesc();
}