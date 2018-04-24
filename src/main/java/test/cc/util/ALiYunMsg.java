package test.cc.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.cc.model.Orders;

@Component
public class ALiYunMsg {

	final static String product = "Dysmsapi";
	final static String domain = "dysmsapi.aliyuncs.com";

	@Value("${sms.accessKeyId}")
	private String accessKeyId;

	@Value("${sms.accessKeySecret}")
	private String accessKeySecret;

	@Value("${sms.signName}")
	private String signName;

	@Value("${sms.templateCode}")
	private String templateCode;
	
	public SendSmsResponse sendSms(String mobile,String templateParam) throws ClientException {
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		

		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (com.aliyuncs.exceptions.ClientException e1) {
			e1.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        try{
        	SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        	return sendSmsResponse;
        }catch (Exception e) {
		}
        return null;
        
    }
	
	public String changecode(Orders order, String cellphone){
		return "{\"name\":\"" + order.getUserName() + "\", \"time\":\"" + DateTimeUtil.formatDateTime(order.getStartTime()) + "\", \"type\":\"" + order.getType() +  "\", \"bz\":\"" + order.getEndResource() +  "\", \"assigner\":\"" + order.getRepair() +  "\", \"cellphone\":\"" + cellphone + "\"}";
	}


}
