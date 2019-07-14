package xyz.cglzwz.dto;

import xyz.cglzwz.entity.SuccessKilled;
import xyz.cglzwz.enums.SeckillStatusEnum;

/**
 * 封装秒杀执行后结果
 * @author chgl16
 * @date 2019/07/13 21:42
 */
public class SeckillExcution {

    private long seckillId;

    /** 秒杀执行结果状态，不同于信息表状态 */
    private int status;

    /** 状态表示 */
    private String statusInfo;

    /** 秒杀成功信息对象 */
    private SuccessKilled successKilled;

    /** 成功构造方法，使用枚举整合状态信息，保留具体属性 */
    public SeckillExcution(long seckillId, SeckillStatusEnum seckillStatusEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
        this.successKilled = successKilled;
    }

    /** 失败构造方法，使用枚举整合状态信息，保留具体属性 */
    public SeckillExcution(long seckillId, SeckillStatusEnum seckillStatusEnum) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
