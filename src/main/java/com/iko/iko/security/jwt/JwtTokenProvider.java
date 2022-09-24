package com.iko.iko.security.jwt;

import com.iko.iko.service.member.CustomUserDetailsService;
import io.jsonwebtoken.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;


@RequiredArgsConstructor
@Component
@Getter
public class JwtTokenProvider {


    @Value("spring.jwt.secret")
    private String secretKey;

    // 토큰 유효시간 168 시간(7일)
    private long ACCESS_TOKEN_VALID_TIME = 1000L* 60 * 30  ; //30분

    private long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7; //일주일

    private final CustomUserDetailsService customUserDetailsService;

    public static final String HEADER_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    public static final String HEADER_REFRESH_TOKEN = "X-REFRESH-TOKEN";

    // secretKey 를 Base64로 인코딩합니다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createAccessToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role); // 정보는 key/value 쌍으로 저장됩니다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // 토큰 유효 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘
                .compact();
    }

//    public String createRefreshToken() {
//        Date now = new Date();
//        return Jwts.builder()
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
    public String createRefreshToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserEmail(String token) {
           return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader(HEADER_ACCESS_TOKEN);
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader(HEADER_REFRESH_TOKEN);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
//        } catch (ExpiredJwtException e) {
//            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // jwt 토큰의 유효성 + 로그아웃 확인 + 만료일자만 초과한 토큰이면 return true;
    public boolean validateTokenExceptExpiration(String jwtToken) {
        try {
//            if (isLoggedOut(jwtToken)) return false;
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰의 만료 시점을 반환 하는 메소드
    public Date getExpiredDate(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getExpiration();
    }
}