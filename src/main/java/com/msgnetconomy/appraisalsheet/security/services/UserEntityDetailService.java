package com.msgnetconomy.appraisalsheet.security.services;

import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntityDetail;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserEntityDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserEntityDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username);

        UserGroupEntity role = user.getUserGroup();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        UserEntityDetail userDetail = new UserEntityDetail();
        userDetail.setUser(user);
        userDetail.setAuthorities(authorities);

        return userDetail;
    }
}
