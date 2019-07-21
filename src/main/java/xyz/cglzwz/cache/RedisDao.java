package xyz.cglzwz.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import xyz.cglzwz.entity.Seckill;

/**
 * redis操作
 * @author chgl16
 * @date 2019/7/21 14:50
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /**
     * 从redis里获取Seckill的byte[]，反序列化
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {
        try {
            // 从池子获取一个连接
            Jedis jedis = jedisPool.getResource();
            try {
                // 构建存储表示的key
                String key = "seckill:" + seckillId;
                // 采用比jdk提供的Serializable接口高效的序列化和反序列化
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    // 缓存获取到
                    // 创建一个空对象
                    Seckill seckill = schema.newMessage();
                    // 把数据赋予到空对象中
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * redis中没有，把seckill对象序列化成字节数组发送到redis
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                // 构造key
                String key = "seckill:" + seckill.getSeckillId();
                // 序列化对象
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 一致性解决方案：超时缓存 1h
                int timeout = 60 * 60;
                // 缓存到redis，result为操作成功失败提示
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
