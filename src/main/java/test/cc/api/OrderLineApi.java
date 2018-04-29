package test.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.OrderFlow;
import test.cc.model.Orders;
import test.cc.repository.OrderFlowRepository;
import test.cc.util.BaseBeanUtil;

import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/20.
 */
@RestController
@RequestMapping("/orderLine")
public class OrderLineApi {

    @Autowired
    private OrderFlowRepository orderFlowRepository;

    @RequestMapping("findByOrderId")
    public Object queryTimeLineByOrderId(@RequestBody Orders Order){
        List<OrderFlow> list = orderFlowRepository.findAllByOrderIdOrderByCreateTimeDesc(Order.getId());
        return BaseBeanUtil.setData(list.size(), list);
    }

    @RequestMapping("/add")
    public Object addTimeLine(@RequestBody OrderFlow orderFlow){
        orderFlow.setCreateTime(new Date());
        orderFlow.setState(1);
        orderFlowRepository.save(orderFlow);
        return BaseBeanUtil.setCode(1,"");
    }
}
