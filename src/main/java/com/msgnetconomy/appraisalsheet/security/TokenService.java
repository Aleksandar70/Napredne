package com.msgnetconomy.appraisalsheet.security;

import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenService {

    private static final String COLON=":";

    @Autowired
    private UserService userService;

    public Authentication parseUserFromToken(String token) {
        final String  decoded = new String(Base64.decodeBase64(token));
        final String  username = decoded.substring(0, decoded.indexOf(COLON));
        UserDto userDto = userService.findByUsername(username);
        List<GrantedAuthority> lg = new ArrayList<>();
        TokenUser tu = new TokenUser(userDto, lg, token);
        tu.setAuthenticated(true);
        return tu;
    }
}
