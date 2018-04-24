package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import test.cc.model.OldPhone;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/23.
 */
public interface OldPhoneRepository extends JpaRepository<OldPhone, Integer>, JpaSpecificationExecutor<OldPhone>{
}
