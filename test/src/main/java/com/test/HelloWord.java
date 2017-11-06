package com.test;

import com.test.dao.UserAppDao;
import com.test.entity.UserApp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class HelloWord {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config/Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(UserAppDao.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            /*
            UserApp user = session.selectOne(
                    "com.test.entity.UserApp.getUserByID", 111941);
            */
            UserAppDao iuser = session.getMapper(UserAppDao.class);
            UserApp user = iuser.getUserById(111941l);
            if(user!=null){
                String userInfo = "名字："+user.getUsername()+", 所属部门："+user.getEquipmentNum()+", 主页："+user.getCityCode();
                System.out.println(userInfo);
            }

            iuser.updateUserApp("test",111941l);

            session.commit();

            user = iuser.getUserById(111941l);
            if(user!=null){
                String userInfo = "名字："+user.getUsername()+", 所属部门："+user.getEquipmentNum()+", 主页："+user.getCityCode();
                System.out.println(userInfo);
            }
        } finally {
            session.close();
        }
    }
}
