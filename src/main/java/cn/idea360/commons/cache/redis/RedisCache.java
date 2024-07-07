package cn.idea360.commons.cache.redis;

import cn.idea360.commons.cache.Cache;
import cn.idea360.commons.cache.RedisClient;

/**
 * @author cuishiying
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private final RedisClient<K, V> redisClient;

    public RedisCache(RedisClient<K, V> redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public V get(K key) {
        return redisClient.get(key);
    }

    @Override
    public void put(K key, V value) {
        redisClient.put(key, value);
    }

    @Override
    public void evict(K key) {
        redisClient.evict(key);
    }
}
