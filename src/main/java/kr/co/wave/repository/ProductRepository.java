package kr.co.wave.repository;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(
            value = """
                      select new kr.co.wave.dto.ProductDTO(
                        p.proId, p.name, p.description
                      )
                      from Product p
                      where
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
                    """
    )
    Page<QnADTO> findQnABySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("answered") Boolean answered, Pageable pageable);


}