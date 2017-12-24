package cn.campus.platfrom;

import com.baomidou.mybatisplus.toolkit.Sequence;
import org.junit.Test;

public class CreateId {

    @Test
    public void test(){
        Sequence sequence=new Sequence();
        long l = sequence.nextId();
        System.out.println(l);
    }

}
