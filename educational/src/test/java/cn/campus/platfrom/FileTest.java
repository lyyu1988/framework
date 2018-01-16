package cn.campus.platfrom;

import cn.campus.platfrom.util.io.FilePersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class FileTest {

    @Autowired
    @Qualifier("fastDFSClient")
    private FilePersistence filePersistence;

    @Test
    public void test() throws Exception {
        File file=new File("d:\\tomcat.keystore");
        String[] strings = filePersistence.uploadFile(file, "", "txt", null);
        System.out.println(strings[0]+"/"+strings[1]);
    }

}
