package test.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.Books;
import test.cc.param.ChartParams;
import test.cc.param.OrdersParams;
import test.cc.repository.BooksRepository;
import test.cc.service.spec.BooksSpec;
import test.cc.service.spec.OrdersSpec;
import test.cc.util.BaseBeanUtil;
import test.cc.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/26.
 */
@RestController
@RequestMapping("/books")
public class BooksApi {

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping("/orderList")
    public Object findOrderList(@RequestBody OrdersParams params){
        PageRequest request = OrdersSpec.buildPageRequest(params.getPageNum());
        Page<Books> books = booksRepository.findAll(
                Specification.where(BooksSpec.requiredType(params.getType())).
                        and(BooksSpec.isTimeBetween(params.getStartTime())),request);
        return BaseBeanUtil.setData((int)books.getTotalElements(), books.getContent());
    }

    @RequestMapping("/edit")
    public Object edit(@RequestBody Books books){
        booksRepository.save(books);
        return BaseBeanUtil.setCode(1,"");
    }

    @RequestMapping("/delete")
    public Object delete(@RequestBody Books books){
        booksRepository.deleteById(books.getId());
        return BaseBeanUtil.setCode(1,"");
    }

    @RequestMapping("/add")
    public Object add(@RequestBody Books books){
        books.setTime(new Date());
        booksRepository.save(books);
        return BaseBeanUtil.setCode(1,"");
    }

    @RequestMapping("/7day")
    public Object sevenDay(){
        List<ChartParams> data = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            Date a = DateTimeUtil.setBeginTimeInDate(DateTimeUtil.addDaysForToday(-32-i));
            Date b = DateTimeUtil.setEndTimeInDate(DateTimeUtil.addDaysForToday(-32-i));
            Integer amount1 = booksRepository.sumAmount("维修",a, b);
            Integer amount2 = booksRepository.sumAmount("二手",a, b);
            data.add(ChartParams.builder().av(amount1==null?0:amount1).bv(amount2==null?0:amount2).name(DateTimeUtil.formatDate(DateTimeUtil.addDaysForToday(-47-i))).build());
        }
        return BaseBeanUtil.setData(1, data).replaceAll("av","维修").replaceAll("bv", "二手");
    }
}
