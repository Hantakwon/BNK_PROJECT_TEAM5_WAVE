package kr.co.wave.repository.board.cs;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.entity.board.cs.QnA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Integer> {

    // 답변 여부 추가
    @Query(
            value = """
                      select new kr.co.wave.dto.QnADTO(
                         q.qnaId, q.title, q.content, q.writer, q.createdAt, q.isAnswered
                      )
                      from QnA q
                      where
                        (:answered is null or q.isAnswered = :answered)
                        and (
                          (:keyword is null or :keyword = '')
                          or (
                            ( :searchType is null or :searchType = '' ) and (
                              lower(q.title)    like lower(concat('%', :keyword, '%')) or
                              lower(q.content)  like lower(concat('%', :keyword, '%')) or
                              lower(q.writer) like lower(concat('%', :keyword, '%'))
                            )
                          )
                          or (:searchType = 'title'    and lower(q.title)    like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'content'  and lower(q.content)  like lower(concat('%', :keyword, '%')))
                          or (:searchType = 'writer' and lower(q.writer) like lower(concat('%', :keyword, '%')))
                        )
                    """
    )
    Page<QnADTO> findQnABySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("answered") Boolean answered, Pageable pageable);

    // 최신글 5개 찾아오기
    List<QnA> findTop5ByOrderByCreatedAtDesc();

}
