package xyz.cglzwz.service.impl;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import xyz.cglzwz.dao.SeckillDao;
import xyz.cglzwz.dao.SuccessKilledDao;
import xyz.cglzwz.dto.Exposer;
import xyz.cglzwz.dto.SeckillExcution;
import xyz.cglzwz.entity.Seckill;
import xyz.cglzwz.entity.SuccessKilled;
import xyz.cglzwz.enums.SeckillStatusEnum;
import xyz.cglzwz.exception.RepeatKillException;
import xyz.cglzwz.exception.SeckillCloseException;
import xyz.cglzwz.exception.SeckillException;
import xyz.cglzwz.service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * 业务实现类
 * @author chgl16
 * @date 2019/07/13 21:53
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /** md5加盐混淆 */
    private final String slat = "adfa$#%65!$%!!@#$";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 系统时间在秒杀之内，暴露秒杀接口
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SeckillExcution excuteSeckill(long seckillId, String userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            // 错误的md5
            throw new SeckillException("秒杀认证错误");
        }

        // 执行秒杀逻辑
        Date nowTime = new Date();

        try {
            // 减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                // 没有成功减库存，而MD5正确，说明库存不足秒杀结束
                throw new SeckillCloseException("秒杀已结束");
            } else {
                // 有更新，一般为1即减库存成功，开始记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    // 没有插入，说明是重复秒杀
                    // 抛出异常前面的减库存也会回滚
                    throw new RepeatKillException("重复秒杀");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStatusEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 所有编译期异常转换为运行时异常
            throw new SeckillException("秒杀内部异常：" + e.getMessage());
        }
    }
}
