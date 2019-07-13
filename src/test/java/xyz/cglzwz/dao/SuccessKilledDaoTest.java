package xyz.cglzwz.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long seckillId = 1000;
        String userPhone = "01234567890";
        // 1表示成功，0表示失败（*已经抢购）
        log.info("" + successKilledDao.insertSuccessKilled(seckillId, userPhone));

    }

    @Test
    public void queryByIdWithSeckill() {
        long seckillId = 1000;
        String userPhone = "01234567890";
        log.info(successKilledDao.queryByIdWithSeckill(seckillId, userPhone).toString());
    }
}