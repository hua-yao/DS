package bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String spuName;
    @Column
    private String description;
    @Column
    private  String catalog3Id;
    /**
     * 保存spu销售属性值
     */
    @Transient
    private List<SpuSaleAttr> spuSaleAttrList;
    /**
     * 保存spu图片
     */
    @Transient
    private List<SpuImage> spuImageList;
}
