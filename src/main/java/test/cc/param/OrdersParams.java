package test.cc.param;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
@Data
public class OrdersParams{
    private Integer id;
    private String searchKey;
    private Date[] startTime;
    private String assigner;
    private List<String> types;
    private String type;
    private int pageNum;
    private Integer state;
    private String oldPhoneState;
}
