package kr.ac.kopo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import redis.clients.jedis.Jedis;

public class RedisSessionUtil {

	private Jedis jedis;

    public RedisSessionUtil(String host, int port) {
        this.jedis = new Jedis(host, port);
    }

    public void saveSession(String sessionId, Serializable sessionObject) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(sessionObject);
            byte[] byteData = bos.toByteArray();
            jedis.set(sessionId.getBytes(), byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getSession(String sessionId) {
        byte[] byteData = jedis.get(sessionId.getBytes());
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

    public void deleteSession(String sessionId) {
        jedis.del(sessionId.getBytes());
    }
	
}
