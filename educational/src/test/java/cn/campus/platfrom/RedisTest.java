package cn.campus.platfrom;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class RedisTest {

    @Test
    public void test(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.102.3", 7000));
        jedisClusterNodes.add(new HostAndPort("192.168.102.2", 7000));
        jedisClusterNodes.add(new HostAndPort("192.168.102.1", 7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);
    }

}
