package com.msgnetconomy.appraisalsheet.security;

import com.msgnetconomy.appraisalsheet.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TokenUser implements Authentication {

    private final UserDto user;
    private boolean isAuth;
    private String token;
    private List<GrantedAuthority> grantedAuthorities;

    public TokenUser(UserDto user, List<GrantedAuthority> grantedAuthorities, String token) {
        super();
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuth;
    }

    @Override
    public void setAuthenticated(boolean bln) {
        this.isAuth = bln;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

}
