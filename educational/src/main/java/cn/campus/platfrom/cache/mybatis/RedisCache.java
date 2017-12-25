package cn.campus.platfrom.cache.mybatis;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

	private static JedisConnectionFactory jedisConnectionFactory;

	private final String id;


	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public RedisCache(final String id){
		if (id == null) {
			throw new IllegalArgumentException("cache instances require an ID");
		}
		this.id = id;
	}


	@Override
	public void clear() {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}
		}

		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			System.out.println("**"+serializer.serialize(key));
			connection.set(serializer.serialize(key), serializer.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}
		}

	}

	@Override
	public Object removeObject(Object key) {
		RedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expireAt(serializer.serialize(key), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}

}
