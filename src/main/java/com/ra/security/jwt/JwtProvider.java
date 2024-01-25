package com.ra.security.jwt;

import com.ra.security.user_principal.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;
    private Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    // tạo token
    public String generateToken(UserPrincipal userPrincipal){
        return Jwts.builder().setSubject(userPrincipal.getUsername())
                // thời gian sống của token
                .setIssuedAt(new Date())
                .setExpiration(new Date( new Date().getTime()+EXPIRED))
                // chữ ký bí mật
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
    // kểm tra token có đúng, còn thời gian sử dụng không,....
    public Boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException expiredJwtException)
        {
            logger.error("Thời gian hết hạn {}",expiredJwtException.getMessage());
        }catch (SignatureException signatureException)
        {
            logger.error("Sai chữ ký {}",signatureException.getMessage());
        }catch (MalformedJwtException malformedJwtException)
        {
            logger.error("Lỗi định dạng {}",malformedJwtException.getMessage());
        }
        return false;
    }
    public String getUserNameFromToken(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
