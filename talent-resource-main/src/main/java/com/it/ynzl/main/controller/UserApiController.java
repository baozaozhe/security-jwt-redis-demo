package com.it.ynzl.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.ynzl.main.common.ResponseUtils;
import com.it.ynzl.main.common.ResultEnum;
import com.it.ynzl.main.entity.User;
import com.it.ynzl.main.service.UserService;
import com.it.ynzl.main.utils.IdWorker;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息控制器类，用户信息资源管理
 * @author: Mr.Muxl
 * @date 2021-12-09
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "用户信息")
public class UserApiController {

    @Resource
    private UserService userService;

    /**
     * 登陆的接口
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆的接口")
    public ResponseUtils<User> login(@RequestBody User user){
        //判断是否为空
        String password = user.getPassword();
        String username = user.getUsername();

        if(null == password||"".equals(password)|| null == username||"".equals(username)){
            return ResponseUtils.Failed("账号密码不能为空");
        }

        //验证用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        User cuser = userService.getOne(wrapper);

        if(null == cuser){
            return ResponseUtils.Failed("账号不存在");
        }

        //验证密码是否正确
        String cpassword = cuser.getPassword();
        String ipassword = user.getPassword();

        if(!cpassword.equals(ipassword)){
            return ResponseUtils.Failed("密码错误");
        }

        return ResponseUtils.Success("登陆成功");
    }

    @RequestMapping(value = "/queryPageInfo",method = RequestMethod.POST)
    @ApiOperation(notes = "用户信息分页", httpMethod = "POST", value = "用户信息分页列表")
    @ApiResponse(code = 200, message = "获取成功", response = User.class)
    public ResponseUtils<IPage<User>> queryPageInfo(
                @RequestParam (value = "pageno",required = true) @ApiParam(value = "pageno", required = true) int pageno,
                @RequestParam (value = "size",required = true)  @ApiParam(value = "size", required = true) int size,
                @RequestBody User  user) {

        Page page = new Page<User>();
        page.setCurrent(pageno);
        page.setSize(15);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);

        IPage<User> pageinfo= userService.page(page,wrapper);

        if(null == pageinfo.getRecords() ||pageinfo.getRecords().isEmpty()){
             return ResponseUtils.Result(ResultEnum.NOT_FOUND);
        }
         return ResponseUtils.Success(pageinfo);
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "根据id获取用户信息信息", httpMethod = "GET", value = "根据id获取用户信息信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(code = 401, message = "令牌已过期，请重新获取", response = User.class)
    public ResponseUtils<User> getUserById(@PathVariable("id") @ApiParam(value = "用户信息id", required = true) long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseUtils.Result(ResultEnum.NOT_FOUND);
        }
        return ResponseUtils.Success(user);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "添加用户信息", httpMethod = "POST", value = "添加用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 201, message = "添加成功", response = User.class),
    @ApiResponse(code = 401, message = "令牌已过期，请重新获取"),
    @ApiResponse(code = 400, message = "添加失败")})
    public ResponseUtils<User> createUser(@RequestBody User user) {
        user.setId(String.valueOf(new IdWorker().nextId()));
        if (userService.save(user)){
            return ResponseUtils.Result(ResultEnum.SUCCESS,user);
        }else {
            return ResponseUtils.Result(ResultEnum.FAILURE);
        }
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "更新用户信息信息", httpMethod = "PUT", value = "更新用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 201, message = "更新成功", response = User.class),
    @ApiResponse(code = 401, message = "令牌已过期，请重新获取"),
    @ApiResponse(code = 400, message = "更新失败")})
    public ResponseUtils<User> updateUser(@RequestBody User user) {
        User curruser = userService.getById(user.getId());

        if (curruser == null) {
            return ResponseUtils.Result(ResultEnum.NOT_FOUND);
        }

        if (userService.updateById(user)){
            return ResponseUtils.Success(user);
        }else {
            return ResponseUtils.Failed();
        }
    }

}
