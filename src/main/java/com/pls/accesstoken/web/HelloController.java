package com.pls.accesstoken.web;

import com.pls.accesstoken.model.Result;
import com.pls.accesstoken.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 81046 on 2018-07-20
 */
@RestController
@RequestMapping("/hello")
@Api("HomeController相关的api")
public class HelloController {

    @ApiOperation(value = "测试访问", notes = "返回指定字符串")
    @GetMapping("/say")
    public Result say(){
        return ResultUtil.success("hello swagger 2");
    }
}
