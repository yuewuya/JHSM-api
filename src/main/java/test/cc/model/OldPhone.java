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
 * Date    2018/04/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="oldphone")
public class OldPhone {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="sfz")
    private String sfz;

    @Column(name="cellphone")
    private String cellphone;

    @Column(name="user_name")
    private String userName;

    @Column(name="type")
    private String type;

    @Column(name="color")
    private String color;

    @Column(name="ram")
    private String ram;

    @Column(name="lmei")
    private String lmei;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="out_time")
    private Date outTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="start_price")
    private int startPrice;

    @Column(name="mid_price")
    private int midPrice;

    @Column(name="end_price")
    private int endPrice;

    @Column(name="assigner")
    private String assigner;

    @Column(name="state")
    private String state;

    @Column(name="tousername")
    private String tousername;

    @Column(name="tocellphone")
    private String tocellphone;

}
