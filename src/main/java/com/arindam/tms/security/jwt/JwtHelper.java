package com.arindam.tms.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 3600000;  // in mili -> 1 hour
    public static final String JWT_SECRET = "jwtauthfjdgcdhdgjbfkfkfbjfghvdhfvfhvfjbfjbfjbfgjgkgnjgbgjbgjbgjjwtauthfjdgcdhdgjbfkfkfbjfghvdhfvfhvfjbfjbfjbfgjgkgnjgbgjbgjbgj";


    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(JWT_SECRET.getBytes()).build()
                .parseClaimsJws(jwtToken).getBody();
//        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken).getBody();
    }

    public <T> T getClaimFromToken(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims = this.getAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String getUserNameFromToken(String jwtToken){
        return this.getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String jwtToken){
        return this.getClaimFromToken(jwtToken, Claims::getExpiration);
    }

    public boolean isTokenExpired(String jwtToken){
        Date expirationDateFromToken = this.getExpirationDateFromToken(jwtToken);
        return expirationDateFromToken.before(new Date());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date( System.currentTimeMillis() + JWT_TOKEN_VALIDITY ))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();



    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userDetails", userDetails);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails){
        final String userName = this.getUserNameFromToken(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

}