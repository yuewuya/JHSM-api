package test.cc.param;

import lombok.Builder;
import lombok.Data;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/26.
 */
@Data
@Builder
public class ChartParams {
    private String name;
    private int av;
    private int bv;
    private int cv;
}
