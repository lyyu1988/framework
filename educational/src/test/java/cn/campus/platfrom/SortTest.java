package cn.campus.platfrom;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTest {

    @Test
    public void test(){
        String[] t={"a","b","d","c"};
        List<String> list=Arrays.asList(t);
        Collections.sort(list);
        System.out.println(list);
    }

}
