package com.jackmouse.authserver.domains;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.*;

/**
 * @ClassName LoginAppUser
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/24 10:19
 * @Version 1.0
 **/
public class LoginAppUser extends User implements OAuth2AuthenticatedPrincipal {

    private final Long id;

    private final String mobile;

    private final Collection<String> menuList;

    private final Map<String, Object> attributes = new HashMap<>();

    public LoginAppUser() {
        super(" ", "", true, true, true, true, new HashSet<>());
        this.id = null;
        this.mobile = null;
        this.menuList = new HashSet<>(0);
    }

    public LoginAppUser(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
        super(username, ""
                , true, true, true, true, authorities);
        this.id = id;
        this.mobile = null;
        this.menuList = new HashSet<>(0);
    }

    public LoginAppUser(Long id, String username, String password, String mobile, Collection<String> permissions, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, Optional.ofNullable(password).orElse("")
                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.mobile = mobile;
        this.menuList = Optional.ofNullable(permissions).orElse(new HashSet<>(0));
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.getUsername();
    }
}
