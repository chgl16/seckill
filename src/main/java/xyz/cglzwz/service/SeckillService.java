package xyz.cglzwz.service;

import xyz.cglzwz.dto.Exposer;
import xyz.cglzwz.dto.SeckillExcution;
import xyz.cglzwz.entity.Seckill;
import xyz.cglzwz.exception.RepeatKillException;
import xyz.cglzwz.exception.SeckillCloseException;
import xyz.cglzwz.exception.SeckillException;

import java.util.List;

/**
 * 秒杀业务接口
 * @author chgl16
 * @date 2019/07/13 21:33
 */
public interface SeckillService {

    /**
     * 获取所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     * @return Exposer
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return seckillExcution
     */
    SeckillExcution excuteSeckill(long seckillId, String userPhone, String md5)
        throws SeckillException, SeckillCloseException, RepeatKillException;
}
