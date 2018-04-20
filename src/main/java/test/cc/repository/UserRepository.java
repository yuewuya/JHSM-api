package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import test.cc.model.User;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/16.
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User>{

    User findByCellphone(String cellphone);
}
