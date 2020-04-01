package bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * spu销售属性值
 */
public class SpuSaleAttr implements Serializable {
    //id
    @Id
    @Column
    private Long id;
    //商品id
    @Column
    private Long spuId;
    //销售属性id
    @Column
    private String saleAttrId;
    //销售属性名称
    @Column
    private String saleAttrName;
}
