package test.cc.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.OldPhone;
import test.cc.model.User;
import test.cc.param.OrdersParams;
import test.cc.repository.OldPhoneRepository;
import test.cc.repository.UserRepository;
import test.cc.service.UserService;
import test.cc.service.spec.OldPhoneSpec;
import test.cc.util.BaseBeanUtil;

import java.util.Date;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
@RestController
@RequestMapping("/oldPhone")
public class OldPhoneApi {

    @Autowired
    private OldPhoneRepository oldPhoneRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public Object ordersList(@RequestBody OrdersParams params){
        PageRequest request = OldPhoneSpec.buildPageRequest(params.getPageNum());
        Page<OldPhone> page = oldPhoneRepository.findAll(
                                Specification.where(OldPhoneSpec.isSearchKeyLike(params.getSearchKey()))
                                        .and(OldPhoneSpec.isAssignerEqual(params.getAssigner()))
                                        .and(OldPhoneSpec.isTypeEqual(params.getType()))
                                        .and(OldPhoneSpec.isStateEqual(params.getOldPhoneState())), request);
        return BaseBeanUtil.setData((int)page.getTotalElements(), page.getContent());
    }

    @RequestMapping("/add")
    public Object addOldPhone(@RequestBody OldPhone oldPhone){
        User user = User.builder()
                .cellphone(oldPhone.getCellphone())
                .name(oldPhone.getUserName())
                .build();
        userService.addYDJUer(user);
        oldPhone.setStartTime(new Date());
        oldPhone.setState("库存");
        oldPhoneRepository.save(oldPhone);
        return BaseBeanUtil.setCode(1, "二手机回收信息已添加");
    }

    @RequestMapping("/edit")
    public Object edit(@RequestBody OldPhone oldPhone){
        oldPhoneRepository.save(oldPhone);
        return BaseBeanUtil.setCode(1, "");
    }

    @RequestMapping("/sell")
    public Object sell(@RequestBody OldPhone oldPhone){
        oldPhoneRepository.save(oldPhone);
        if (StringUtils.isNotEmpty(oldPhone.getCellphone())){
            User user = User.builder()
                    .cellphone(oldPhone.getTocellphone())
                    .name(oldPhone.getTousername())
                    .build();
            user = userService.addYDJUer(user);
            user.setAmount(user.getAmount() + (oldPhone.getEndPrice() == 0 ? oldPhone.getMidPrice() : oldPhone.getEndPrice()));
            user.setLevel(user.getLevel() + 1);
            userRepository.save(user);
        }
        return BaseBeanUtil.setCode(1, "");
    }

    @RequestMapping("/delete")
    public Object deleteOrder(@RequestBody OldPhone oldPhone){
        oldPhoneRepository.deleteById(oldPhone.getId());
        return BaseBeanUtil.setCode(1, "");
    }

}
