package com.it.ynzl.main.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.ynzl.main.entity.User;
import com.it.ynzl.main.security.entity.SysUserDetails;
import com.it.ynzl.main.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 用户登录Service
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/10 12:58
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService sysUserService;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser = sysUserService.getOne(new QueryWrapper<User>().eq("username",username));
        if (sysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(sysUser, sysUserDetails);

            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

//            List<SysRole> roleList = sysUserService.findRoleByUserId(sysUserDetails.getId());
//            roleList.forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//            });

            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            sysUserDetails.setAuthorities(authorities);

            return sysUserDetails;
        }
        return null;
    }

}