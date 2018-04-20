package test.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.cc.model.User;
import test.cc.repository.UserRepository;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addYDJorder(User user){
        User old = userRepository.findByCellphone(user.getCellphone());
        if (old == null){
            userRepository.save(user);
        }
    }
}
