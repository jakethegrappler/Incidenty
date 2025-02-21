package cz.cvut.fel.nss.SaunaStudio.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

/**
 * Konfigurační třída pro nastavení cache pomocí Redis.
 * Tato třída obsahuje konfiguraci pro správu cache pomocí Redis, včetně nastavení
 * pro RedisCacheManager a RedisTemplate.
 */
@Configuration
public class CacheConfig {

    /**
     * Definuje bean pro správu cache pomocí RedisCacheManager.
     *
     * <p>Nastavuje výchozí konfiguraci cache, která zahrnuje dobu životnosti položek
     * v cache na 20 minut a zakáže cachování null hodnot.</p>
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory} pro připojení k Redis serveru.
     * @return instance {@link RedisCacheManager} pro správu cache.
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(20))
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /**
     * Definuje bean pro RedisTemplate.
     *
     * <p>Šablona pro operace s Redis, nastavuje serializer pro hodnoty na {@link GenericToStringSerializer}.</p>
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory} pro připojení k Redis serveru.
     * @return instance {@link RedisTemplate} pro práci s Redis.
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }
}
