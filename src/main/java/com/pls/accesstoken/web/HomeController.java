package com.pls.accesstoken.web;

import com.pls.accesstoken.model.Result;
import com.pls.accesstoken.service.AccessTokenService;
import com.pls.accesstoken.service.SellerPublicService;
import com.pls.accesstoken.util.PullAccessTokenUtils;
import com.pls.accesstoken.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getAccessTokenByAppId(@RequestParam("appid") String appid){
        return ResultUtil.success(accessTokenService.getAccessTokenByAppId(appid).getAccessToken());
    }

    /**
     * 这种是根据appid查询出整个对象，然后再获取需要的值
     * @param appid
     * @return
     */
    @PostMapping("/findByAppId")
    public Result findByAppId(@RequestParam("appid") String appid){
        return ResultUtil.success(accessTokenService.findByAppId(appid).getAccessToken());
    }

    /**
     * 这种方式根据appid查询出结果值
     * @param appid
     * @return
     */
    @PostMapping("/findAccessByAppId")
    public Result findAccessByAppId(@RequestParam("appid") String appid){
        return ResultUtil.success(accessTokenService.findAccessByAppId(appid));
    }

    /**
     * 获取所有的公众号信息
     */
    @GetMapping("/getAllList")
    public Result getAllList(){
        return ResultUtil.success(sellerPublicService.getAllList());
    }

    /**
     * 这种方式根据appid查询出结果值
     * @param appid
     * @return
     */
    @PostMapping("/getToken")
    public Result getToken(@RequestParam("appid") String appid,@RequestParam("appsecret") String appsecret){
        return ResultUtil.success(PullAccessTokenUtils.getAccessToken(appid,appsecret));
    }

}
