package kr.co.wave.repository.board.company;

import kr.co.wave.dto.board.company.EventDTO;
import kr.co.wave.entity.board.company.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    // 은행 소개 - 이벤트
    /*
    @Query(
            value = """
                      select new kr.co.wave.dto.board.company.EventDTO(
                         e.eventId, e.title, e.content, e.writer, e.createdAt
                      )
                      from Event e
                      where
                          (:keyword is null or :keyword = '')
                          or (
                            ( :searchType is null or :searchType = '' ) and (
                              lower(e.title)    like lower(concat('%', :keyword, '%')) or
                              lower(cast(e.content as string))  like lower(concat('%', :keyword, '%')) or
                              lower(e.writer) like lower(concat('%', :keyword, '%'))
                            )
                          )
                          or (:searchType = 'title'    and lower(e.title)    like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'content'  and lower(cast(e.content as string))  like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'writer' and lower(e.writer) like lower(concat('%', :keyword, '%')))
                    """
    )
    Page<EventDTO> findEventBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);
    */
    @Query(
            value = """
                  SELECT e.notice_id, e.title, e.content, e.writer, e.created_at
                  FROM TB_COMPANY_EVENT e
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
    Page<Object[]> findEventBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);

    // 최신글 5개 찾아오기
    List<Event> findTop5ByOrderByCreatedAtDesc();
}