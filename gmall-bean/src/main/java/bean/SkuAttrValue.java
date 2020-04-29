package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * sku平台属性值关联表
 * @author huayao
 */
@Data
public class SkuAttrValue implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    /**
     * 属性id（冗余）
     */
    @Column
    private Long attrId ;
    /**
     * 库存Id
     */
    @Column
    private Long skuId ;
    /**
     * 属性值id
     */
    @Column
    private Long valueId ;
}
