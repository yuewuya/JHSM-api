package test.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.cc.model.Admin;
import test.cc.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/19.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin add(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin findById(String id){
        return adminRepository.findFirstById(id);
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }
}
