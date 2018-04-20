package test.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.cc.model.Message;
import test.cc.repository.MessageRepository;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/11.
 */
@Service
public class MessageSerivce {

    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message){
        return messageRepository.save(message);
    }
}
