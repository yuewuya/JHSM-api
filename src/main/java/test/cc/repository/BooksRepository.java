package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import test.cc.model.Books;

import java.util.Date;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/26.
 */
public interface BooksRepository extends JpaRepository<Books, Integer>, JpaSpecificationExecutor<Books> {

    Books findByBooktypeAndSearchKey(String booktype, int key);

    @Query("select SUM(b.amount) from Books b where b.booktype like concat('%',?1,'%') and b.time > ?2 and b.time < ?3")
        Integer sumAmount(String type, Date a, Date b);
}
