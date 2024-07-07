package cn.idea360.commons.cache;

/**
 * @author cuishiying
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void evict(K key);
}

