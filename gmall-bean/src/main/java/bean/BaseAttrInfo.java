package bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 平台属性
 * @author: HuaYao
 * @create: 2020-01-06 21:58
 **/
@Data
public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;
    @Transient
    List<BaseAttrValue> attrValueList;
}