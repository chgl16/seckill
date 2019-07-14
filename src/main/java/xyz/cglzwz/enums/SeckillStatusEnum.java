package xyz.cglzwz.enums;

public enum SeckillStatusEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPERT_KILL(-1, "重复秒杀"),
    DATA_REWRITE(-2, "数据篡改"),
    INNER_ERROR(-3, "系统异常");

    private int status;

    private String statusInfo;

    SeckillStatusEnum(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public static String getStatusInfo(int status) {
        for (SeckillStatusEnum seckillStatusEnum : SeckillStatusEnum.values()) {
            if (seckillStatusEnum.getStatus() == status)
                return seckillStatusEnum.statusInfo;
        }
        return null;
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
}
