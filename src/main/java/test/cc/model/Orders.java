package test.cc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Orders {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="userName")
    private String userName;

    @Column(name="cellphone")
    private String cellphone;

    @Column(name="type")
    private String type;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name="start_resource")
    private String startResource;

    @Column(name="end_resource")
    private String endResource;

    @Column(name="start_price")
    private Double startPrice;

    @Column(name="end_price")
    private Double endPrice;

    @Column(name="remark")
    private String remark;

    @Column(name="repair")
    private String repair;

    @Column(name="state")
    private Integer state;

}
