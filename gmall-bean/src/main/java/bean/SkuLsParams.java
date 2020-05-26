package bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SkuLsParams implements Serializable {

    /**
     *
     */
    String  keyword;

    /**
     * 三级分类id
     */
    String catalog3Id;

    /**
     * 值id
     */
    String[] valueId;

    /**
     * 页码
     */
    int pageNo=1;

    /**
     * 一页多少表数据
     */
    int pageSize=20;

    List<String> valueIdList = new ArrayList<>();

    public void changeArray2List() {
        for (int i = 0; i < valueId.length;i++) {
            String id = valueId[i];
            valueIdList.add(id);
        }

    }
}























