package kr.ac.kopo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private Jedis jedis;
	
	public RedisUtil(String host, int port) {
		this.jedis = new Jedis(host, port);
	}
	
	public void saveObject(String key, Serializable object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            byte[] byteData = bos.toByteArray();
            jedis.set(key.getBytes(), byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObject(String key) {
    	byte[] byteData = jedis.get(key.getBytes());
        if (byteData == null) {
            return null;
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(byteData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void deleteKey(String key) {
    	jedis.del(key.getBytes());
    }
    
}
