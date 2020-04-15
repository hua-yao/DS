package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *spu图片信息保存
 * @author huayao
 */
@Data
public class SpuImage implements Serializable {
    @Id
    @Column
    private Long id;
    @Column
    private String spuId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;
}
