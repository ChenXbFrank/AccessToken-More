package com.pls.accesstoken.web;

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

    /**
     * 这种是根据appid查询出整个对象，然后再获取需要的值
     * @param appid
     * @return
     */
    @PostMapping("/getAccessTokenByAppId")
    public String getAccessTokenByAppId(@RequestParam("appid") String appid){
        return accessTokenService.getAccessTokenByAppId(appid).getAccessToken();
    }

    /**
     * 这种是根据appid查询出整个对象，然后再获取需要的值
     * @param appid
     * @return
     */
    @PostMapping("/findByAppId")
    public String findByAppId(@RequestParam("appid") String appid){
        return accessTokenService.findByAppId(appid).getAccessToken();
    }

    /**
     * 这种方式根据appid查询出结果值
     * @param appid
     * @return
     */
    @PostMapping("/findAccessByAppId")
    public String findAccessByAppId(@RequestParam("appid") String appid){
        return accessTokenService.findAccessByAppId(appid);
    }

    /**
     * 获取所有的公众号信息
     */
    @GetMapping("/getAllList")
    public List<SellerPublicNumber> getAllList(){
        return sellerPublicService.getAllList();
    }

}
