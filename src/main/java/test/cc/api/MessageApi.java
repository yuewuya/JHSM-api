package test.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.Admin;
import test.cc.model.Message;
import test.cc.model.Orders;
import test.cc.param.MessageParams;
import test.cc.param.OrdersParams;
import test.cc.repository.MessageRepository;
import test.cc.repository.OrdersRepository;
import test.cc.service.OrdersService;
import test.cc.service.spec.OrdersSpec;
import test.cc.util.BaseBeanUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
@RestController
@RequestMapping("/message")
public class MessageApi {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/list")
    public Object ordersList(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Message> all = messageRepository.findAll(sort);
        return BaseBeanUtil.setData(all.size(), all);
    }

    @RequestMapping("/do")
    public Object isToFind(@RequestBody MessageParams params){
        Message m = messageRepository.findFirstByIdGreaterThan(params.getId());
        if (m == null)
            return BaseBeanUtil.setCode(0,"");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Message> all = messageRepository.findAll(sort);
        return BaseBeanUtil.setData(1, all.size(), all);
    }

    @RequestMapping("/add")
    public Object add(@RequestBody Message message){
        message.setCreateDate(new Date());
        messageRepository.save(message);
        return BaseBeanUtil.setCode(1, "");
    }

    @RequestMapping("/delete")
    public Object add(@RequestBody MessageParams params){
        messageRepository.deleteById(params.getId());
        return BaseBeanUtil.setCode(1, "");
    }
}
