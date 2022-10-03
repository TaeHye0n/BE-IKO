package com.iko.iko.security.jwt;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    public static String getLoginUserEmail() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        // 비로그인 접근
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BaseException(ErrorCode.NEED_LOGIN);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


}
