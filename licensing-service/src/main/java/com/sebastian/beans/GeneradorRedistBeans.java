package com.sebastian.beans;

import com.sebastian.config.ServiceConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisShardInfo;

@Component
public class GeneradorRedistBeans {
    private static final Logger LOGGER = Logger.getLogger(GeneradorRedistBeans.class);

    @Autowired
    private ServiceConfig sc;

    @Bean
    public JedisConnectionFactory jedisConnectioniFactory() {
        JedisConnectionFactory jc = new JedisConnectionFactory();
        jc.setHostName(sc.getRedisServer());
        jc.setPort(sc.getRedisPort());
        LOGGER.info("JCF: " + jc);
        LOGGER.info("JCF: " + sc.getRedisServer());
        LOGGER.info("JCF: " + sc.getRedisPort());
        JedisShardInfo si = new JedisShardInfo(sc.getRedisServer(), sc.getRedisPort());
        jc.setShardInfo(si);
        LOGGER.info("JCF: " + jc.getConnection());
        return jc;
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        rt.setConnectionFactory(jedisConnectioniFactory());
        LOGGER.info("redis template: " + rt);
        return rt;
    }
}
