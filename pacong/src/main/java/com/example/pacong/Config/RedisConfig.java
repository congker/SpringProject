package com.example.pacong.Config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by xuwencong on 2019/4/10
 */
//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /*
     * 生成Key策略
     * */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {

            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /*
     * 管理缓存
     * */
//    @Bean
//    public CacheManager cacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration){
//        return new RedisCacheManager(cacheWriter,defaultCacheConfiguration);
//    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /*
     * redis模板配置
     * */
    @Bean
    public RedisTemplate<String, String> redisTemplateV2() {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
