package cn.idea360.commons.cache;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author cuishiying
 */
public class CacheBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {

        if (isRedisAvailable()) {
            BeanDefinition redisClientBeanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(RedisClient.class)
                    .getBeanDefinition();
            registry.registerBeanDefinition("redisClient", redisClientBeanDefinition);
        }

        BeanDefinition factoryBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(CacheFactoryBean.class)
                .addPropertyReference("redisClient", "redisClient")
                .getBeanDefinition();

        registry.registerBeanDefinition("cacheFactoryBean", factoryBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // No implementation needed
    }

    private boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }

    private boolean isRedisAvailable() {
        return isPresent("redis.clients.jedis.Jedis");
    }
}
