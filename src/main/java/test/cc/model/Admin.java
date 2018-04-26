package test.cc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="pwd")
    private String pwd;

    @Column(name="role")
    private Integer role;

    @Column(name="kq")
    private Integer kq;

    @Column(name="js")
    private Integer js;

    @Column(name="yw")
    private Integer yw;

    @Column(name="num")
    private Integer num;

}
