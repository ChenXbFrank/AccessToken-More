package com.pls.accesstoken.dao;

import com.pls.accesstoken.model.AccessTokens;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * 这里是处理accessToken的数据交互层
 * Created by 81046 on 2018-07-12
 */
public interface AccessTokenDao {

    /**
     * 根據APPID查詢AccessTokens
     * @param appid
     * @return
     */
    @Select("select * from TB_ACCESS_TOKENS where appid = #{appid}")
    AccessTokens getAccessTokenByAppId(@Param("appid")String appid);

    /**
     * 根據APPID查詢AccessToken
     * @param appid
     * @return
     */
    @Select("select access_token from TB_ACCESS_TOKENS where appid = #{appid}")
    String getTokenByAppId(@Param("appid")String appid);

    /**
     * 插入新的AccessTokens
     * @param appid
     * @param accessToken
     * @param saveTime
     */
    @Insert("insert into TB_ACCESS_TOKENS(appid,access_token,save_time) values(#{appid},#{accessToken},#{saveTime})")
    @Options(useGeneratedKeys = true, keyProperty = "appid", keyColumn = "appid")
    @Transactional
    void save(@Param("appid") String appid,@Param("accessToken")String accessToken, @Param("saveTime")String saveTime);

    /**
     * 根据APPID修改AccessTokens
     * @param accessToken
     * @param saveTime
     * @param appid
     * @return
     */
    @Transactional
    @Update({ "update TB_ACCESS_TOKENS set access_token = #{accessToken},save_time = #{saveTime} where appid = #{appid}" })
    int updateAccessTokensByAppId(@Param("accessToken")String accessToken, @Param("saveTime")String saveTime,@Param("appid") String appid);
}
