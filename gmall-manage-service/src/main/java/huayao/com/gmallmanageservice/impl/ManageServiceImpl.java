package huayao.com.gmallmanageservice.impl;

import bean.*;
import com.alibaba.dubbo.config.annotation.Service;
import huayao.com.gmallmanageservice.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import service.ManageService;

import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 业务类
 * @author: HuaYao
 * @create: 2020-01-06 22:14
 **/
@Service
public class ManageServiceImpl implements ManageService {
    private BaseCatalog1Mapper baseCatalog1Mapper;
    private BaseCatalog2Mapper baseCatalog2Mapper;
    private BaseCatalog3Mapper baseCatalog3Mapper;
    private BaseAttrInfoMapper baseAttrInfoMapper;
    private BaseAttrValueMapper baseAttrValueMapper;
    private SpuInfoMapper spuInfoMapper;
    private BaseSaleAttrMapper baseSaleAttrMapper;
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    public ManageServiceImpl(BaseCatalog1Mapper baseCatalog1Mapper, BaseCatalog2Mapper baseCatalog2Mapper, BaseCatalog3Mapper baseCatalog3Mapper, BaseAttrInfoMapper baseAttrInfoMapper, BaseAttrValueMapper baseAttrValueMapper, SpuInfoMapper spuInfoMapper, BaseSaleAttrMapper baseSaleAttrMapper, SpuImageMapper spuImageMapper) {
        this.baseCatalog1Mapper = baseCatalog1Mapper;
        this.baseCatalog2Mapper = baseCatalog2Mapper;
        this.baseCatalog3Mapper = baseCatalog3Mapper;
        this.baseAttrInfoMapper = baseAttrInfoMapper;
        this.baseAttrValueMapper = baseAttrValueMapper;
        this.spuInfoMapper = spuInfoMapper;
        this.baseSaleAttrMapper = baseSaleAttrMapper;
        this.spuImageMapper = spuImageMapper;
    }

    /**
     * 获取一级分类
     *
     * @return 一级分类
     */
    @Override
    public List<BaseCatalog1> getCatalog1List() {
        List<BaseCatalog1> baseCatalog1s = baseCatalog1Mapper.selectAll();
        return baseCatalog1s;
    }

    /**
     * 获取二级分类
     *
     * @return 二级分类
     */
    @Override
    public List<BaseCatalog2> getCatalog2List(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        //直接传入对象
        List<BaseCatalog2> baseCatalog2s = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2s;
    }

    /**
     * 获取三级分类
     * @return 三级分类
     */
    @Override
    public List<BaseCatalog3> getCatalog3List(String Catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(Catalog2Id);
        List<BaseCatalog3> baseCatalog3s = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3s;
    }

    /**
     * 获取平台属性
     * @param catalog3Id 三级分类id
     * @return
     */
    @Override
    public List<BaseAttrInfo> attList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectAttrInfoList(Long.parseLong(catalog3Id));
        return baseAttrInfos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.insertSelective(baseAttrInfo);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue : attrValueList) {
            String id = baseAttrInfo.getId();
            baseAttrValue.setAttrId(id);
            baseAttrValueMapper.insertSelective(baseAttrValue);
        }
    }

    @Override
    public List<SpuInfo> getSpuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> select = spuInfoMapper.select(spuInfo);
        return select;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        List baseSaleAttrList = baseSaleAttrMapper.selectAll();
        return baseSaleAttrList;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //判断空键
        if (spuInfo.getId() != null && spuInfo.getId().length() == 0) {
            spuInfo.setId(null);
        }
        spuInfoMapper.insertSelective(spuInfo);
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : spuImageList) {
            String id = spuInfo.getId();
            spuImage.setSpuId(id);
            spuImageMapper.insertSelective(spuImage);
        }
        //执行删除，在保存
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insertSelective(spuSaleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSpuId(spuInfo.getId());
                spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }
        }
    }

    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectAttrInfoList(Long.parseLong(catalog3Id));
        return baseAttrInfos;
    }

}






















