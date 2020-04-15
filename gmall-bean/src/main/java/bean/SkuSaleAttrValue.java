package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * sku销售属性值
 * @author huayao
 */
@Data
public class SkuSaleAttrValue implements Serializable {
    @Id
    @Column
   private Long id;
    /**
     * 库存单元id
     */
    @Column
    private String skuId ;
    /**
     * 销售属性id
     */
    @Column
   private String saleAttrId;
    /**
     * 销售属性名称
     */
    @Column
   private String saleAttrValueId;
    /**
     *销售属性值id
     */
    @Column
   private String saleAttrName;
    /**
     *销售属性值名称
     */
    @Column
   private String saleAttrValueName;
}
