package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * spu销售属性值
 * @author huayao
 */
@Data
public class SpuSaleAttr implements Serializable {
    @Id
    @Column
    private Long id;
    @Column
    private String spuId;
    @Column
    private String saleAttrId;
    @Column
    private String saleAttrName;
    @Transient
    List<SpuSaleAttrValue> spuSaleAttrValueList;
    @Transient
    Map spuSaleAtrValueJson;
}
