package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author huayao
 */
@Data
public class SpuSaleAttrValue implements Serializable {
    @Id
    @Column
    private Long id;
    /**
     * 商品id
     */
    @Column
    private String spuId;
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
    /**
     *
     */
    @Transient
    private String isChecked;
}
