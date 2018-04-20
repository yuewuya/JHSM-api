package test.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.Admin;
import test.cc.model.Orders;
import test.cc.model.User;
import test.cc.param.OrdersParams;
import test.cc.repository.OrdersRepository;
import test.cc.repository.UserRepository;
import test.cc.service.OrdersService;
import test.cc.service.UserService;
import test.cc.service.spec.OrdersSpec;
import test.cc.util.BaseBeanUtil;

import java.util.Date;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
@RestController
@RequestMapping("/orders")
public class OrdersApi {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${cc.test.value}")
    private String msg;

    @RequestMapping("/list")
    public Object ordersList(@RequestBody OrdersParams params){
        PageRequest request = OrdersSpec.buildPageRequest(params.getPageNum());
        Page<Orders> page = ordersRepository.findAll(
                                Specification.where(OrdersSpec.isSearchKeyLike(params.getSearchKey()))
                                        .and(OrdersSpec.isIdEqual(params.getId()))
                                        .and(OrdersSpec.isStartTimeBetween(params.getStartTime()))
                                        .and(OrdersSpec.isAssignerEqual(params.getAssigner()))
                                        .and(OrdersSpec.isTypeIn(params.getTypes()))
                                        .and(OrdersSpec.isStateEqual(params.getState())), request);
        return BaseBeanUtil.setData((int)page.getTotalElements(), page.getContent());
    }

    @RequestMapping("/findById")
    public Object findById(@RequestBody Orders order){
        return BaseBeanUtil.setData(ordersRepository.findById(order.getId()).get(), 1);
    }

    @RequestMapping("/YDJorders")
    public Object YDJorders(@RequestBody OrdersParams params){
        Page<Orders> page = ordersRepository.findAllByState(-1,new PageRequest(params.getPageNum(), 10));
        return BaseBeanUtil.setData((int)page.getTotalElements(), page.getContent());
    }

    @RequestMapping("/50day")
    public Object last50DayOKNum(){
        return BaseBeanUtil.setData(msg,1, ordersService.last50DayOKNum());
    }

    @RequestMapping("/addYDJorder")
    public Object addYDJorder(@RequestBody Orders order){
        User user = User.builder()
                .cellphone(order.getCellphone())
                .name(order.getUserName())
                .build();
        userService.addYDJorder(user);
        order.setState(-1);
        order.setStartTime(new Date());
        ordersRepository.save(order);
        return BaseBeanUtil.setCode(1, "信息已登记，待员工审核");
    }
}
