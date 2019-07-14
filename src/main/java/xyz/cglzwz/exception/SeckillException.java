package xyz.cglzwz.exception;

/**
 * 所有秒杀异常父类
 * @author chgl16
 * @date 2019/07/13 21:50
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
