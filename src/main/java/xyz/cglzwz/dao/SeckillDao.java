package xyz.cglzwz.dao;

import xyz.cglzwz.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * @author chgl16
 * @date 2019/07/13 10:09
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime 秒杀时间
     * @return  如果影响行数>1，表示更新的记录行数
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据商品库存id获取秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset 从0开始
     * @param limit  条数
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);
}
