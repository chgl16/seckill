package xyz.cglzwz.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.cglzwz.dto.Exposer;
import xyz.cglzwz.dto.SeckillExcution;
import xyz.cglzwz.entity.Seckill;
import xyz.cglzwz.service.SeckillService;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list = {}", list);
    }

    @Test
    public void getById() {
        int id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("secKill = {}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        int id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer = {}", exposer);
    }

    @Test
    public void excuteSeckill() {
        int id = 1000;
        String userPhone = "18718803003";
        // 先获取md5 b032c651afcf2e6e547e52d8f8177f42
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            SeckillExcution seckillExcution = seckillService.excuteSeckill(id, userPhone, exposer.getMd5());
            System.out.println(seckillExcution);
        } else {
            // 秒杀没有开始

        }

    }
}