package cn.idea360.commons.cache;

import cn.idea360.commons.cache.memory.CaffeineCache;
import cn.idea360.commons.cache.redis.RedisCache;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author cuishiying
 */
public class CacheFactoryBean<K, V> implements FactoryBean<Cache<K, V>>, InitializingBean {

    private Cache<K, V> cache;
    private RedisClient<K, V> redisClient;

    public void setRedisClient(RedisClient<K, V> redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public Cache<K, V> getObject() throws Exception {
        return cache;
    }

    @Override
    public Class<?> getObjectType() {
        return Cache.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Cache<K, V> level1Cache = new CaffeineCache<>();
        Cache<K, V> level2Cache = redisClient != null ? new RedisCache<>(redisClient) : null;
        cache = new MultiLevelCache<>(level1Cache, level2Cache != null ? level2Cache : level1Cache);
    }

}
