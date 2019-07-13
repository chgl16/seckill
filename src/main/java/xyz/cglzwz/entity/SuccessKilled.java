package xyz.cglzwz.entity;

import java.util.Date;

/**
 * 秒杀成功表
 *
 * @author chgl16
 * @date 2019/07/13 09:56
 */
public class SuccessKilled {

    private long seckillId;

    private String userPhone;

    private short status;

    private Date createTime;

    /** 便于查看 */
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone='" + userPhone + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }
}
