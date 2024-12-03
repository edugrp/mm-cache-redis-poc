package org.example.cachemodule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CachingConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${cache:none}")
    CacheImplementation cacheImplementation;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        logger.info("Creating cache manager: {}", cacheImplementation);

        return switch (cacheImplementation) {
            case none -> new NoOpCacheManager();
            case local -> createLocalCacheManager();
            case ehcache -> createEhCacheManager();
            case redis -> createRedisCacheManager(connectionFactory);
            default -> throw new IllegalStateException("Unexpected value: " + cacheImplementation);
        };
    }

    private CacheManager createLocalCacheManager() {
        ConcurrentMapCacheManager cm = new ConcurrentMapCacheManager();
        cm.setStoreByValue(true);
        return cm;
    }

    private CacheManager createEhCacheManager() {
        return new ConcurrentMapCacheManager();
    }

    private CacheManager createRedisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
    }

    public enum CacheImplementation {
        none, local, ehcache, redis
    }
}
