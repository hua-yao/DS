package huayao.com.gmallmanageservice.impl;

import bean.*;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import huayao.com.gmallmanageservice.constant.RedisConst;
import huayao.com.gmallmanageservice.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisAskDataException;
import service.ListService;
import service.ManageService;


import java.util.ArrayList;
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
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Reference
    ListService listService;

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

    @Override
    public List<BaseCatalog1> getCatalog1List() {
        List<BaseCatalog1> baseCatalog1s = baseCatalog1Mapper.selectAll();
        return baseCatalog1s;
    }

    @Override
    public List<BaseCatalog2> getCatalog2List(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        //直接传入对象
        List<BaseCatalog2> baseCatalog2s = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2s;
    }

    @Override
    public List<BaseCatalog3> getCatalog3List(String Catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(Catalog2Id);
        List<BaseCatalog3> baseCatalog3s = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3s;
    }

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

    @Override
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectAttrInfoList(Long.parseLong(catalog3Id));
        return baseAttrInfos;
    }

    @Override
    public List<SpuSaleAttr> getSaleAttrList(String spuId) {
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectSaleAttrInfoList(Long.parseLong(spuId));
        return spuSaleAttrList;
    }

    @Override
    public List<SpuSaleAttr> selectSaleAttrInfoListBySku(String spuId, String skuId) {
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectSaleAttrInfoListBySku(Long.parseLong(skuId), Long.parseLong(spuId));
        return spuSaleAttrList;
    }

    @Override
    public List<SpuImage> getSpuImageList(String spuId) {
        SpuImage spuImageQuery = new SpuImage();
        spuImageQuery.setSpuId(spuId);
        List<SpuImage> select = spuImageMapper.select(spuImageQuery);
        return select;
    }

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insertSelective(skuInfo);
        SkuImage skuImageDel = new SkuImage();
        skuImageDel.setId(skuInfo.getId());
        skuImageMapper.delete(skuImageDel);
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(skuImage);
        }
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(skuAttrValue);
        }
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
        }
    }

    @Override
    public SkuInfo getSKuInfo(Long skuId) {
        try {
            Jedis jedis = redisUtil.getJedis();
            String skuKey = RedisConst.SKU_PREFIX + String.valueOf(skuId) + RedisConst.SKU_SUFFIX;
            String skuInfoJson = jedis.get(skuKey);
            if (skuInfoJson != null && skuInfoJson.length() > 0) {
                System.out.println(Thread.currentThread().getName() + ": 命中缓存");
                SkuInfo skuInfo1 = JSON.parseObject(skuInfoJson, SkuInfo.class);
                jedis.close();
                return skuInfo1;
            } else {
                System.out.println(Thread.currentThread().getName() + ": 未命中缓存");
                System.err.println(Thread.currentThread().getName() + "： 查询数据##################### ##");
                //获得锁和加锁最好是原子性的，否则可能出现并发问题
                //设置一把锁
                String skuLockKey = RedisConst.SKU_PREFIX + String.valueOf(skuId) + RedisConst.SKULOCK_SUFFIX_;
                //第四个参数 ex是秒数，px是毫秒数,第五个参数是取数据库取值，3秒失效，然后让出该锁
                String ifLocked = jedis.set(skuLockKey, "locked", "NX", "EX", RedisConst.SKULOCK_EXPIRE_PX);
                if (ifLocked == null) {
                    System.err.println(Thread.currentThread().getName() + "未获得分布式锁，开始自旋！");
                    try {
                        //设置休眠时间
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //没有锁，线程自旋,不能频繁自旋，设置休眠时间
                    return getSKuInfo(skuId);
                } else {
                    //有锁了之后取查询数据库
                    System.err.println(Thread.currentThread().getName() + "获得分布式锁！");
                    SkuInfo skuInfoDB = getSkuInfoDB(skuId);
                    //取值为empty 表示是一个不存在的值
                    if ("empty".equals(skuInfoJson)) {
                        return null;
                    }
                    //加入用户查询要给不存在的值，那么就在redis中存储empty
                    if (skuInfoDB == null) {
                        jedis.setex(skuKey, RedisConst.SKU_TIMEOUT, "empty");
                    } else {
                        String skuInfoJsonStr = JSON.toJSONString(skuInfoDB);
                        //setex方法可以设置失效时间
                        jedis.setex(skuKey, RedisConst.SKU_TIMEOUT, skuInfoJsonStr);
                        jedis.close();
                        return skuInfoDB;
                    }
                }
            }
        } catch (JedisAskDataException e) {
        }
        return getSkuInfoDB(skuId);
    }

    private SkuInfo getSkuInfoDB(Long skuId) {
        //selectByPrimaryKey :按主键查询
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        List<SkuImage> skuImageList = skuImageMapper.select(skuImage);
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuId);
        List<SkuAttrValue> skuAttrValueList = skuAttrValueMapper.select(skuAttrValue);

        skuInfo.setSkuAttrValueList(skuAttrValueList);
        skuInfo.setSkuImageList(skuImageList);
        return skuInfo;
    }


    @Override
    public List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId) {
        List<SkuSaleAttrValue> skuSaleAttrValues = skuSaleAttrValueMapper.selectSkuSaleAttrValueListBySpu(Long.parseLong(spuId));
        return skuSaleAttrValues;
    }

    @Override
    public void onSale(String skuId) {
        SkuInfo sKuInfo = getSKuInfo(Long.parseLong(skuId));
        SkuLsInfo skuLsInfo = new SkuLsInfo();
        //拷贝
        BeanUtils.copyProperties(sKuInfo, skuLsInfo);
        List<SkuLsAttrValue> skuLsAttrValues = new ArrayList<>();
        List<SkuAttrValue> skuAttrValueList = sKuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            SkuLsAttrValue skuLsAttrValue = new SkuLsAttrValue();
            skuLsAttrValue.setValueId(skuAttrValue.getValueId());
            skuLsAttrValues.add(skuLsAttrValue);
        }
        skuLsInfo.setSkuAttrValueList(skuLsAttrValues);
        listService.saveSkuInfoEs(skuLsInfo);
    }

    @Override
    public List<BaseAttrInfo> getPropertyValue(List attrInfoValueId){
        //join 传入值，然后根据逗号进行拼接
        String ValueId = StringUtils.join(attrInfoValueId, ',');
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectAttrInfoValueId(ValueId);
        return baseAttrInfos;
    }
}









































