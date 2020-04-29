package bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 库存表
 * @author huayao
 */
@Data
public class SkuInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    /**
     * 三级分类id（冗余）
     */
    @Column
    private Long catalog3Id ;
    /**
     * 价格
     */
    @Column
    private BigDecimal price ;
    /**
     * 默认显示图片（冗余）
     */
    @Column
    private String skuDefaultImg ;
    /**
     * 商品规格描述
     */
    @Column
    private String skuDesc ;
    /**
     * sku名称
     */
    @Column
    private String skuName ;
    /**
     * 商品id
     */
    @Column
    private Long spuId ;
    /**
     *品牌（冗余）
     */
    @Column
    private String tmId ;
    /**
     * 重量
     */
    @Column
    private BigDecimal weight ;
    @Transient
    List<SkuImage> skuImageList;
    @Transient
    List<SkuAttrValue> skuAttrValueList;
    @Transient
    List<SkuSaleAttrValue> skuSaleAttrValueList;

}





















