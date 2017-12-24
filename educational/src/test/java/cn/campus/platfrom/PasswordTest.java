package cn.campus.platfrom;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class PasswordTest {

    @Test
    public void test(){
        char[] p=new char[]{'1','2','3','4','5','6'};
        SimpleHash hash = new SimpleHash("SHA-256", p, ByteSource.Util.bytes("aaa"), 10);
        String base64 = hash.toBase64();
        String hex = hash.toHex();
        String string = hash.toString();

        System.out.println("base64="+base64);
        System.out.println("hex="+hex);
        System.out.println("string="+string);
    }

}
