package cn.campus.platfrom;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test(){
        char[] p=new char[]{'1','2','3','4','5','6'};
        SimpleHash hash = new SimpleHash("SHA-256", p, null, 1);
        String base64 = hash.toBase64();
        String hex = hash.toHex();
        String string = hash.toString();
        Base64.decode(base64);

        System.out.println("base64="+Base64.decode(base64));
        System.out.println("hex="+hex);
        System.out.println("string="+string);
    }

}
