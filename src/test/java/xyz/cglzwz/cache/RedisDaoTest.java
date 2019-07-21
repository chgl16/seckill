package xyz.cglzwz.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.cglzwz.dao.SeckillDao;
import xyz.cglzwz.entity.Seckill;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {
    private int id = 1000;

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private RedisDao redisDao;

    @Test
    public void getAndPutSeckill() {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                // 存入redis
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                // 再取出来
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }
}