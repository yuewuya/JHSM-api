package test.cc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name="cellphone")
    private String cellphone;

    @Column(name="name")
    private String name;

    @Column(name="pwd")
    private String pwd;

    @Column(name="amount")
    private double amount;

    @Column(name="level")
    private Integer level;

    @Column(name="remark")
    private String remark;

    @Column(name="sfz")
    private String sfz;

    @Column(name="label")
    private String label;

    @Column(name="score")
    private Integer score;

}
