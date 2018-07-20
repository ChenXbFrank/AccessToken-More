package com.pls.accesstoken.service;

import com.pls.accesstoken.dao.AccessTokenDao;
import com.pls.accesstoken.model.AccessTokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 获取数据库里面的token service
 * Created by 81046 on 2018-07-12
 */
@Service
public class AccessTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenService.class);

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据appid获取accesstoken
     * @param appid
     * @return
     *  192.168.0.3:8089/home/getAccessTokenByAppId?appid=wx3db6ea684d097397
     */
    public AccessTokens getAccessTokenByAppId(String appid){
        // 从缓存中获取accesstoken信息
        String key = "accessToken_" + appid;
        ValueOperations<String, AccessTokens> operations = redisTemplate.opsForValue();

        // 缓存存在  直接返回
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            AccessTokens accessTokens = operations.get(key);
            LOGGER.info("AccessTokenService.getAccessTokenByAppId() : 从缓存中获取了accesstoken >> " + accessTokens.toString());
            return accessTokens;
        }

        // 从 DB 中获取accesstoken信息
        AccessTokens accessTokens = accessTokenDao.getAccessTokenByAppId(appid);
        // 插入缓存
        operations.set(key, accessTokens, 1000, TimeUnit.SECONDS);
        LOGGER.info("AccessTokenService.getAccessTokenByAppId() : accesstoken插入缓存 >> " + accessTokens.toString());
        return accessTokens;
    }

    /**
     * 根據APPID查詢AccessToken
     * @param appid
     * @return
     */
    public String getTokenByAppId(String appid){
        return accessTokenDao.getTokenByAppId(appid);
    }

    /**
     * 保存新的accessTokens对象
     * @param appid
     * @param accessToken
     * @param saveTime
     */
    public void save(String appid,String accessToken,String saveTime){
        accessTokenDao.save(appid,accessToken,saveTime);
    }

    /**
     * 根据APPID修改AccessTokens
     * @param accessToken
     * @param saveTime
     * @param appid
     * @return
     */
    public int updateAccessTokensByAppId(String accessToken,String saveTime,String appid){
        int updateAccessTokensByAppId = accessTokenDao.updateAccessTokensByAppId(accessToken, saveTime, appid);
        // 缓存存在，删除缓存
        String key = "accessToken_" + appid;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除accessTokens key值是 >> " + key);
        }
        return updateAccessTokensByAppId;
    }

    /**
     * 根據APPID查詢AccessToken  xml为文件
     * @param appid
     * @return
     */
    public AccessTokens findByAppId(String appid){
        return accessTokenDao.findByAppId(appid);
    }

    /**
     * 根据appid直接查询accessToken  xml文件
     * @param appid
     * @return
     */
    public String findAccessByAppId(String appid){
        return accessTokenDao.findAccessByAppId(appid);
    }
}
