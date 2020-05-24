package bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuLsInfo implements Serializable {

    Long id;

    BigDecimal price;

    String skuName;

    String skuDesc;

    Long catalog3Id;

    String skuDefaultImg;

    Long hotScore = 0L;

    List<SkuLsAttrValue> skuAttrValueList;
}
