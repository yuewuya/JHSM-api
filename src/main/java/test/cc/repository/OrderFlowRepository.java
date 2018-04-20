package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import test.cc.model.OrderFlow;

import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/20.
 */
public interface OrderFlowRepository extends JpaRepository<OrderFlow, Integer>, JpaSpecificationExecutor<OrderFlow> {

    List<OrderFlow> findAllByOrderIdOrderByDateTimeDesc(int orderId);
}
