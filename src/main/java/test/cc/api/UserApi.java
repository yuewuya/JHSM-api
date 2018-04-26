package test.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.User;
import test.cc.param.OrdersParams;
import test.cc.repository.UserRepository;
import test.cc.service.spec.UserSpec;
import test.cc.util.BaseBeanUtil;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/24.
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    public Object findAll(@RequestBody OrdersParams params){
        PageRequest request = UserSpec.buildPageRequest(params.getPageNum());
        Page<User> list = userRepository.findAll(
                Specification.where(UserSpec.isSearchKeyLike(params.getSearchKey())),request);
        return BaseBeanUtil.setData((int)list.getTotalElements(), list.getContent());
    }

    @RequestMapping("/delete")
    public Object deleteOrder(@RequestBody User user){
        userRepository.deleteById(user.getCellphone());
        return BaseBeanUtil.setCode(1, "");
    }
}
