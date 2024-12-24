package com.jackmouse.authserver.utils;


import com.jackmouse.authserver.domains.LoginAppUser;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.entity.SysRole;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.vo.SysUserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class LoginUserUtils {

    public static LoginAppUser getLoginAppUser(SysUserVO sysUser) {
        List<SysRole> sysRoles = sysUser.getRoleList();
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (sysRoles != null) {
            sysRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));
        }

        return new LoginAppUser(sysUser.getId()
                , sysUser.getUsername(), sysUser.getPassword()
                , sysUser.getPhone(), sysUser.getMenuList().stream().map(SysMenu::getPath).toList()
                , sysUser.getEnabled() == 1, true, true, true
                , authorities);
    }
}
