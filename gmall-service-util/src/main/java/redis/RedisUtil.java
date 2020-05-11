package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Redis工具类
 * @author huayao
 */
public class RedisUtil {

    private JedisPool jedisPool;

    /**
     * redis初始化
     * 创建JedisPool对象
     * @param host
     * @param port
     * @param database
     */
    public void initPool(String host,int port,int database){
    /*    JedisPoolConfig poolConfig = new JedisPoolConfig();
        //设置最大总数
        poolConfig.setMaxTotal(200);
        //设置最大空闲
        poolConfig.setMaxIdle(30);
        //耗尽时设置阻塞
        poolConfig.setBlockWhenExhausted(true);
        //设置最大等待毫秒数
        poolConfig.setMaxWaitMillis(10*1000);
        //测试从连接池取连接，如果没有这个连接就创建一个连接
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig, host,port,20*1000);*/
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(30);
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(10*1000);
        poolConfig.setTestOnBorrow(true);
        jedisPool=new JedisPool(poolConfig,host,port,20*1000);
    }

    /**
     * 获取jedis连接
     * @return
     */
    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
