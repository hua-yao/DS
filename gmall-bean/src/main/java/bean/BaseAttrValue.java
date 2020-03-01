package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: dainShangDemo
 * @description: 实体类
 * @author: HuaYao
 * @create: 2020-01-06 21:58
 **/
@Data
public class BaseAttrValue implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }
}
