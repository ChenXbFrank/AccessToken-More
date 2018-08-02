package com.pls.accesstoken.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pls.accesstoken.model.Result;
import com.pls.accesstoken.model.User;
import com.pls.accesstoken.repository.UserRepository;
import com.pls.accesstoken.service.UserService;
import com.pls.accesstoken.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 81046 on 2018-07-20
 */
@RestController
@RequestMapping("/user")
@Api("UserController相关的api")
//解决跨域的注解
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取所有的用户", notes = "获取所有的用户")
    @GetMapping(value = "/getUserList",produces = "application/json; charset=utf-8")
    public Result getUserList(){
        return ResultUtil.success(userRepository.findAll());
    }

    /**
     * 需要分页显示，使用mybatis
     */
    @ApiOperation(value = "获取所有的用户分页显示", notes = "获取所有的用户分页显示")
    @GetMapping(value = "/getUsersByPage",produces = "application/json; charset=utf-8")
    public Result getUsersByPage(){
        // int pageNum, 当前页
        //int pageSize, 每页的条数
        PageHelper.startPage(1,20);
        List<User> usersList = userService.getAllUserList();
        //返回集合对象给页面
        PageInfo<User> pageInfo=new PageInfo<>(usersList);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 根据名字模糊查询 分页显示
     *     http://localhost:8087/user/getUserByName?name=%E5%B0%8F
     */
    @ApiOperation(value = "根据名字模糊查询的用户分页显示", notes = "根据名字模糊查询的用户分页显示")
    @GetMapping(value = "/getUserByName",produces = "application/json; charset=utf-8")
    public Result getUserByName(@RequestParam("name") String name){
        // int pageNum, 当前页
        //int pageSize, 每页的条数
        PageHelper.startPage(1,20);
        List<User> usersList = userService.getUserListByName(name);
        //这是测试出现异常的情况
        //int i = 1/0;
        //返回集合对象给页面
        PageInfo<User> pageInfo=new PageInfo<>(usersList);
        return ResultUtil.success(pageInfo);
    }


    /**
     * 这里测试保存10万条用户信息 (模拟数据)
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @GetMapping(value = "/saveUsers",produces = "application/json; charset=utf-8")
    public Result saveUsers(){
        for (int i = 0; i < 100001; i++) {
            User user=new User();
            user.setId(i+"");
            user.setName("测试"+i);
            user.setAge(10+i);
            userRepository.save(user);
        }
        return ResultUtil.success();
    }

    /**
     * 这里导出所有的用户信息
     * @param response
     * @throws IOException 212
     */
    @RequestMapping(value = "/exportUserInfo", method = RequestMethod.GET)
    public void exportUserInfo(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<User> userList = userRepository.findAll();

        String fileName = "userInfo"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "序号", "姓名", "年龄"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (User user : userList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getName());
            row1.createCell(2).setCellValue(user.getAge());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
