package cn.idea360.commons.cache.redis;

import cn.idea360.commons.cache.RedisClient;
import redis.clients.jedis.Jedis;

/**
 * @author cuishiying
 */
public class JedisClient<K, V> implements RedisClient<K, V> {

    private final Jedis jedis;

    public JedisClient(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public V get(K key) {
        return (V) jedis.get(key.toString());
    }

    @Override
    public void put(K key, V value) {
        jedis.set(key.toString(), value.toString());
    }

    @Override
    public void evict(K key) {
        jedis.del(key.toString());
    }
}
