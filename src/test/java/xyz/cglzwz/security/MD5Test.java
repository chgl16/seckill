package xyz.cglzwz.security;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {
    @Test
    public void test() {
        String slat = "xafd65!@#$%6";
        String key = "123";
        String base = key + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println(md5);
    }
}
