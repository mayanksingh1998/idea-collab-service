package com.finbox.idea_collab_service.config;

import com.finbox.idea_collab_service.client.serializer.CustomJacksonRedisSerializer;
import com.finbox.idea_collab_service.client.serializer.RedisSerializer;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Configuration
public class RedisConfig {

    @Value("redis")
    private String host;

    @Value("6379")
    private Integer port;

    @Value("false")
    private Boolean ssl;

    @Bean
    public StatefulRedisMasterReplicaConnection<String, byte[]> createConnection() {
        RedisURI redisUri = RedisURI.Builder.redis(host, port).withSsl(ssl).build();
        RedisClient redisClient = RedisClient.create();
        List<RedisURI> nodes = List.of(redisUri);
        final RedisCodec<String, byte[]> codec = RedisCodec.of(new StringCodec(), new ByteArrayCodec());
        final StatefulRedisMasterReplicaConnection<String, byte[]> connection =
                MasterReplica.connect(redisClient, codec, nodes);
        connection.setReadFrom(ReadFrom.MASTER_PREFERRED);
        return connection;
    }

    @Bean
    public RedisConnectionFactory createConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);
        lettuceConnectionFactory.setUseSsl(ssl);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }
    @Bean
    public RedisSerializer getRedisSerializer() {
        return new CustomJacksonRedisSerializer();
    }
}
