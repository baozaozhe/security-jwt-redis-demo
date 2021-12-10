package com.it.ynzl.main.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.ynzl.main.entity.User;
import com.it.ynzl.main.mapper.UserMapper;
import com.it.ynzl.main.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息 Service实现类
 * @author: Mr.Muxl
 * @date 2021-12-09
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Resource
	private UserMapper usermapper;
}
