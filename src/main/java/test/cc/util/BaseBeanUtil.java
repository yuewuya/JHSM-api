package test.cc.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


public class BaseBeanUtil {
	
	/**
	 * 返回列表信息的jason
	 * @param count
	 * @param
	 * @return
	 */
	public static String setData(int count, List list){
		StringWriter str=new StringWriter();
		ObjectMapper om=new ObjectMapper();
		try {
			om.writeValue(str, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + str.toString() + "}";
	}

    public static String setData(int code, int count, List list){
        StringWriter str=new StringWriter();
        ObjectMapper om=new ObjectMapper();
        try {
            om.writeValue(str, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"code\":"+ code +",\"msg\":\"\",\"count\":" + count + ",\"data\":" + str.toString() + "}";
    }
	
	public static String setData(String msg, int count, List list){
		StringWriter str=new StringWriter();
		ObjectMapper om=new ObjectMapper();
		try {
			om.writeValue(str, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"code\":0,\"msg\":\"" + msg + "\",\"count\":" + count + ",\"data\":" + str.toString() + "}";
	}

    public static String setData(String msg, Object T){
        StringWriter str=new StringWriter();
        ObjectMapper om=new ObjectMapper();
        try {
            om.writeValue(str, Arrays.asList(T));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"code\":0,\"msg\":\"" + msg + "\",\"count\":1,\"data\":" + str.toString() + "}";
    }

    public static String setData( Object T, int code){
        StringWriter str=new StringWriter();
        ObjectMapper om=new ObjectMapper();
        try {
            om.writeValue(str, Arrays.asList(T));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"code\":"+ code +",\"msg\":\"\",\"count\":1,\"data\":" + str.toString() + "}";
    }

	public static String setCode(int code ,String msg){
		return "{\"code\":" + code + ",\"msg\":\"" + msg + "\",\"count\":1}";
	}

}
