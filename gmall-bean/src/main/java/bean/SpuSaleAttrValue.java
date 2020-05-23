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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
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

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
