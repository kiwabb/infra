package com.jackmouse.authserver.authentication.handlers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName CustomUserDetailService
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/20 15:38
 * @Version 1.0
 **/
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
