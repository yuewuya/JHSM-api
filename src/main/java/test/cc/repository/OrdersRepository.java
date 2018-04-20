package test.cc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import test.cc.model.Orders;

import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public interface OrdersRepository extends JpaRepository<Orders,Integer>, JpaSpecificationExecutor<Orders> {

    @Query("select count(o) from Orders o where o.state = 2  and o.endTime > ?1 and o.endTime < ?2 ")
    int countAllByEndTimeBetween(String a, String b);

    Page<Orders> findAllByState(int state, Pageable pageable);
}
