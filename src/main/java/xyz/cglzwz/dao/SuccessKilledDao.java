package xyz.cglzwz.dao;

import org.apache.ibatis.annotations.Param;
import xyz.cglzwz.entity.SuccessKilled;

import java.util.List;

/**
 * @author chgl16
 * @date 2019/07/13 10:17
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数（1），0失败
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

    /**
     * 根据id查询成功购买明细对象并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);
}

