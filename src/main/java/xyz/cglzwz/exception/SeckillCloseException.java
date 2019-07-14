package xyz.cglzwz.exception;

/**
 * 秒杀关闭异常：时间结束，库存为空
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
