package test.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import test.cc.model.Admin;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
    Admin findAllByIdAndAndPwd(String id, String pwd);

    Admin findFirstByName(String name);

    Admin findFirstById(String id);
}
