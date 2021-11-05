package com.pastley.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;
import java.util.Date;

import com.pastley.security.entity.PrimaryUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication){
        PrimaryUser primaryUser = (PrimaryUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(primaryUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token expirado");
        }catch (IllegalArgumentException e){
            logger.error("token vacío");
        }catch (SignatureException e){
            logger.error("fail en la firma");
        }
        return false;
    }
}
