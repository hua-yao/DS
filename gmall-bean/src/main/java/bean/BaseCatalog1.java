package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: dainShangDemo
 * @description: 一级分类
 * @author: HuaYao
 * @create: 2020-01-06 21:56
 **/
@Data
public class BaseCatalog1 implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String name;
}
