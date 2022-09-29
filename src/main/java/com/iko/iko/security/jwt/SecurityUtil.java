package com.iko.iko.security.jwt;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    public static String getLoginUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof AnonymousAuthenticationToken) {
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
        return userDetails.getUsername();
    }


}
