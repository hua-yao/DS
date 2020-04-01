package bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class spuSaleAttrValue implements Serializable {
    @Id
    @Column
    private Long id;
    /**
     * 商品id
     */
    @Column
    private Long spuId;
    /**
     * 销售属性id
     */
    @Column
    private String saleAttrId;
    /**
     * 销售属性值名称
     */
    @Column
    private String saleAttrValueName;
}
