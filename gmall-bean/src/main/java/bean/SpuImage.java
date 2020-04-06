package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *spu图片信息保存
 * @author huayao
 */
@Data
public class SpuImage {
    @Id
    @Column
    private Long id;
    @Column
    private Long spuId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;
}
