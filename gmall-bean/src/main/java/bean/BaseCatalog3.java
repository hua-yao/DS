package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: dainShangDemo
 * @description: 三级分类
 * @author: HuaYao
 * @create: 2020-01-06 21:57
 **/
@Data
public class BaseCatalog3 implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String catalog2Id;
}