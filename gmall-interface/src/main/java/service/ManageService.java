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

    /**
     * 查询平台属性
     * @param catalog3Id 三级分类ID
     * @return
     */
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id);

    /**
     * 查询销售属性
     * @param spuId 商品ID
     * @return
     */
    public List<SpuSaleAttr> getSaleAttrList(String spuId);

    /**
     * 从spu中查询属于这个商品的sku,主要是用于标记
     * @param spuId
     * @param skuId
     * @return
     */
    public List<SpuSaleAttr> selectSaleAttrInfoListBySku(String spuId,String skuId);

    /**
     * 查询spu图片
     * @param spuId
     * @return
     */
    public List<SpuImage> getSpuImageList(String spuId);

    /**
     * 保存sku
     * @param skuInfo
     */
    public void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 查询出skuInfo 和 skuImage数据
     * @param skuId
     * @return
     */
    public SkuInfo getSKuInfo(Long skuId);

    /**
     * 根据spuId查询出相同的sku属性值Id进行拼接，动态切换
     * @param spuId
     * @return
     */
    public List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId);

    /**
     * 查询出skuInfo和skuAttrValue数据
     * @param skuId
     */
    public void onSale(String skuId);

    /**
     * 获取平台属性和平台属性值
     * @param valueId
     * @return
     */
    public List<BaseAttrInfo> getPropertyValue(List valueId);
}
