package huayao.com.gmallmanageservice.constant;

/**
 * redis常量类
 * @author huayao
 */
public class RedisConst {

    public static final String SKU_PREFIX="sku:";

    public static final String SKU_SUFFIX=":info";

    /**
     * 分布式锁定义
     */
    public static final String SKULOCK_SUFFIX_=":lock";
    public static final int SKULOCK_EXPIRE_PX =3;
    /**
     * 京东的失效时间是一天
     */
    public static final int SKU_TIMEOUT=60;
}
