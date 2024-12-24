package com.jackmouse.authserver.authentication.handlers;

import com.jackmouse.authserver.utils.LoginUserUtils;
import com.jackmouse.basicsystem.api.SysUserDubboService;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.vo.SysUserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * @ClassName CustomUserDetailService
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/20 15:38
 * @Version 1.0
 **/
@Service
public class CustomUserDetailService implements UserDetailsService {

    @DubboReference
    private SysUserDubboService sysUserDubboService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVO sysUser = sysUserDubboService.loadUserByUsername(username);
        return Optional.ofNullable(sysUser)
                .map(user -> LoginUserUtils.getLoginAppUser(sysUser))
                .orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
    }

}
