package test.cc.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.cc.model.Admin;
import test.cc.model.Orders;
import test.cc.repository.AdminRepository;
import test.cc.util.ALiYunMsg;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/23.
 */
@Service
public class SendSMSService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ALiYunMsg aLiYunMsg;

    public SendSmsResponse sendRepairSMS(Orders order){
        Admin a = adminRepository.findFirstByName(order.getRepair());
        String assignerCellphone = a == null ? "15605159823" : a.getId();
        String params = aLiYunMsg.changecode(order, assignerCellphone);
        try {
            return aLiYunMsg.sendSms(order.getCellphone(), params);
        }catch (Exception e){
            return null;
        }

    }
}
