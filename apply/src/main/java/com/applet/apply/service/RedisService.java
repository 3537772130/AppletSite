package com.applet.apply.service;

import com.applet.apply.entity.ViewAppletInfo;
import com.applet.common.util.NullUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/23
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
@Component
public class RedisService {
    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;


    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

//    /**
//     * 删除多个key
//     *
//     * @param keys
//     */
//    public void deleteKey(String... keys) {
//        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
//        redisTemplate.delete(kSet);
//    }
//
//    /**
//     * 删除Key的集合
//     *
//     * @param keys
//     */
//    public void deleteKey(Collection<String> keys) {
//        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
//        redisTemplate.delete(kSet);
//    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }

    public void setRedisValue(String key, Object object) {
        JSONObject obj = JSONObject.fromObject(object);
        redisTemplate.opsForValue().set(key, obj.toString());
        redisTemplate.expire(key, -1, TimeUnit.SECONDS);
    }

    public Object getRedisValue(String key) {
//        redisTemplate.opsForValue().set(key, null);
        String json = (String) redisTemplate.opsForValue().get(key);
        if (NullUtil.isNotNullOrEmpty(json)){
            JSONObject obj = JSONObject.fromObject(json);
            ViewAppletInfo info = (ViewAppletInfo) JSONObject.toBean(obj, ViewAppletInfo.class);
        }
        Object object = (Object) redisTemplate.opsForValue().get(key);
        return object;
    }

}
