package cn.idea360.commons.cache;

/**
 * @author cuishiying
 */
public class MultiLevelCache<K, V> implements Cache<K, V> {

    private final Cache<K, V> level1Cache;
    private final Cache<K, V> level2Cache;

    public MultiLevelCache(Cache<K, V> level1Cache, Cache<K, V> level2Cache) {
        this.level1Cache = level1Cache;
        this.level2Cache = level2Cache;
    }

    @Override
    public V get(K key) {
        V value = level1Cache.get(key);
        if (value == null && level2Cache != null) {
            value = level2Cache.get(key);
            if (value != null) {
                level1Cache.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        level1Cache.put(key, value);
        if (level2Cache != null) {
            level2Cache.put(key, value);
        }
    }

    @Override
    public void evict(K key) {
        level1Cache.evict(key);
        if (level2Cache != null) {
            level2Cache.evict(key);
        }
    }
}

