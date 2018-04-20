package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import test.cc.model.Message;
import test.cc.model.Orders;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public interface MessageRepository extends JpaRepository<Message,Integer>, JpaSpecificationExecutor<Message> {

    Message findFirstByIdGreaterThan(int id);
}
