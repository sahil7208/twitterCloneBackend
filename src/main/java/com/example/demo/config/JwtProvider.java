//package com.example.demo.config;
//
//import java.util.Date;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//import com.example.demo.config.JwtConstant;
//
//@Service
//public class JwtProvider {
////SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//SecretKey key = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(JwtConstant.SECRET_KEY));
//
//
//    public String generateToken(Authentication auth){
//
//        String authorities = String.join(",", auth.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toArray(String[]::new));
//        return Jwts.builder()
//            .setSubject(auth.getName())
//    .setIssuedAt(new Date())
//    .setExpiration(new Date(new Date().getTime()+86400000))
//    .claim("email", auth.getName()).claim("authorities", authorities).signWith(key)
//    .compact();
//}
//
//public String getEmailFromToken(String token){
//    if (token != null && token.startsWith("Bearer ")) {
//        token = token.substring(7);
//    }
//    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    return claims.get("email", String.class); // Directly return the email
//}
//}
