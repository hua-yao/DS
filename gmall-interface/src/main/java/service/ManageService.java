package service;

import bean.*;

import java.util.List;

/**
 * @author huayao
 */
public interface ManageService {
    /**
     * 获取一级分类
     * @return
     */
    public List<BaseCatalog1> getCatalog1List();

    /**
     * 获取二级分类
     * @param Catalog1Id 一级分类id
     * @return
     */
    public List<BaseCatalog2> getCatalog2List(String Catalog1Id);

    /**
     * 获取三级分类
     * @param Catalog2Id) 二级分类id
     * @return
     */
    public List<BaseCatalog3> getCatalog3List(String Catalog2Id);

    /**
     * 获取平台属性
     * @param catalog3Id 三级分类id
     * @return
     */
    public List<BaseAttrInfo> attList(String catalog3Id);

    /**
     * 保存
     * @param baseAttrInfo
     */
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     *根据三级id查询spu商品属性
    * @param catalog3Id
     * @return
     */
    public List<SpuInfo> getSpuList(String catalog3Id);

    /**
     *
     * @return
     */
    public List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * spu保存
     * @param spuInfo
     */
    public void saveSpuInfo(SpuInfo spuInfo);
}
