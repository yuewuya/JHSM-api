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
@Table(name="books")
public class Books {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="time")
    private Date time;

    @Column(name="booktype")
    private String booktype;

    @Column(name="bookname")
    private String bookname;

    @Column(name="cost")
    private int cost;

    @Column(name="inmoney")
    private int inmoney;

    @Column(name="amount")
    private int amount;

    @Column(name="remark")
    private String remark;

    @Column(name="searchkey")
    private Integer searchKey;

}
