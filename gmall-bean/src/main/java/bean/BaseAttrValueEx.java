package bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huayao
 */
@Data
public class BaseAttrValueEx extends BaseAttrValue implements Serializable {
    private String wholeName;
    /**
     * 删除的路径
     */
    private String cancelUrlParam;
}
