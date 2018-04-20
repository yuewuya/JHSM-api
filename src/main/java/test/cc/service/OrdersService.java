package test.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.cc.repository.OrdersRepository;
import test.cc.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/08.
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    /**
     * 最近50天的完成情况
     * @return
     */
    public List<Integer> last50DayOKNum(){
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            String a = DateTimeUtil.formatDateTime(DateTimeUtil.setBeginTimeInDate(DateTimeUtil.addDaysForToday(-i)));
            String b = DateTimeUtil.formatDateTime(DateTimeUtil.setEndTimeInDate(DateTimeUtil.addDaysForToday(-i)));
            counts.add(ordersRepository.countAllByEndTimeBetween(a, b));
        }
        return counts;
    }
}
