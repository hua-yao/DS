package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 基本销售属性表
 */
@Data
public class BaseSaleAttr implements Serializable {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
}
