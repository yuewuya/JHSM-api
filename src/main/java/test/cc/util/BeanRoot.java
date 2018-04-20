package test.cc.util;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeanRoot {
    private int count;
    private String msg;
    private List data;

    public BeanRoot(int count, List data){
        this.count = count;
        this.data = data;
    }
}
