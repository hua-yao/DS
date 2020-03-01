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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public void setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }
}
