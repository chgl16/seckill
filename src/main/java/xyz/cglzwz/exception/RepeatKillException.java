package xyz.cglzwz.exception;

/**
 * 重复秒杀异常
 * @author chgl16
 * @date 2019/07/13 21:46
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
