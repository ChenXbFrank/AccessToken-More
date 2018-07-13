package com.pls.accesstoken.web;

import com.pls.accesstoken.model.AccessTokens;
import com.pls.accesstoken.model.SellerPublicNumber;
import com.pls.accesstoken.service.AccessTokenService;
import com.pls.accesstoken.service.SellerPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 81046 on 2018-07-12
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private SellerPublicService sellerPublicService;

    @PostMapping("/hello")
    public String hello(@RequestParam("appid") String appid){
        String tokenByAppId = accessTokenService.getTokenByAppId(appid);
        return "hello world "+tokenByAppId;
    }

    @PostMapping("/getTokenByAppId")
    public String getTokenByAppId(@RequestParam("appid") String appid){
        return accessTokenService.getTokenByAppId(appid);
    }

    /**
     * 获取getAccessToken只需要传递appid过来即可
     * @param appid
     * @return
     */
    @PostMapping("/getAccessTokenByAppId")
    public String getAccessTokenByAppId(@RequestParam("appid") String appid){
        AccessTokens accessTokenByAppId = accessTokenService.getAccessTokenByAppId(appid);
        return accessTokenByAppId.getAccessToken();
    }

    /**
     * 获取所有的公众号信息
     */
    @GetMapping("/getAllList")
    public List<SellerPublicNumber> getAllList(){
        return sellerPublicService.getAllList();
    }

}
