package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: dainShangDemo
 * @description: spu商品属性
 * @author: HuaYao
 * @create: 2020-01-09 13:11
 **/
@Data
public class SpuInfo implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String spuName;
    @Column
    private String description;
    @Column
    private  String catalog3Id;
}
