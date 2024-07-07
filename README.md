## 使用方法

1. 注册CacheBeanDefinitionRegistryPostProcessor bean
2. 注册缓存
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    @Bean
    public Cache<String, String> cache(CacheFactoryBean<String, String> factoryBean) throws Exception {
        return factoryBean.getObject();
    }
}
```
