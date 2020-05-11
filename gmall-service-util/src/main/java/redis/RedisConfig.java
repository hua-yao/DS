package redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huayao
 */
@Configuration
public class RedisConfig {

    /**
     * 如果spring.redis.host没有值，就将disabled付给它
     */
    @Value("${spring.redis.host:disabled}")
    private String host;

    @Value("${spring.redis.port:0}")
    private int port;

    @Value("${spring.redis.database:0}")
    private int database;

    /**
     * 将RedisUtil类的initPool方法注入到spring容器里面进行初始化：整个项目一启动就创建一次
     * 如果host的值是disabled:表示不用创建redis，因为继承了这个模块的子模块不需要redis,就是没有配置reids参数
     * @return
     */
    @Bean
    public RedisUtil getRedisUtil(){
        if(host.equals("disabled")){
            return null;
        }
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.initPool(host,port,database);
        return redisUtil;
    }

}
