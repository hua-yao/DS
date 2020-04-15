package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 库存单元图片
 * @author huayao
 */
@Data
public class SkuImage implements Serializable {
    @Id
    @Column
    private Long id ;
    /**
     * 图片名称
     */
    @Column
    private String imgName ;
    /**
     * 图片路径
     */
    @Column
    private String imgUrl ;
    /**
     * 是否默认
     */
    @Column
    private String isDefault ;
    /**
     * 商品id
     */
    @Column
    private String skuId ;
    /**
     * 商品图片id
     */
    @Column
    private String spuImgId ;
}
