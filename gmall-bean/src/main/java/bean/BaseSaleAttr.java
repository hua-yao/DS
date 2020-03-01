package bean;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 基本销售属性表
 */
public class BaseSaleAttr {
    @Id
    @Column
    private Long id;
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
