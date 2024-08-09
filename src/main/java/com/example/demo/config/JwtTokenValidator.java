//package com.example.demo.config;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.List;
//
//import javax.crypto.SecretKey;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//public class JwtTokenValidator extends OncePerRequestFilter {
//@Override
////protected void doFilterInternal(
////   @NonNull HttpServletRequest request,
////   @NonNull HttpServletResponse response,
////   @NonNull FilterChain filterChain)
////throws ServletException, IOException{
////    String jwt = request.getHeader(JwtConstant.JWT_HEADER);
////
////    if(jwt != null && jwt.startsWith("Bearer ")){
////        jwt = jwt.substring(7);
////        try {
////           // SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
////            SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JwtConstant.SECRET_KEY));
////
////            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
////           // Claims claims = Jwts.parser().set
////            String email = String.valueOf(claims.get("email",String.class));
////            String authorities = String.valueOf(claims.get("authorities",String.class));
////            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
////            Authentication authentication = new UsernamePasswordAuthenticationToken(email,null, auths);
////
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////        } catch (Exception e) {
////
////            throw new BadCredentialsException("Invalid token",e);
////
////        }
////
////    }
////    filterChain.doFilter(request, response);
////}
//protected void doFilterInternal(
//        @NonNull HttpServletRequest request,
//        @NonNull HttpServletResponse response,
//        @NonNull FilterChain filterChain)
//        throws ServletException, IOException {
//    String header = request.getHeader(JwtConstant.JWT_HEADER);
//
//    if (header != null && header.startsWith("Bearer ")) {
//        String token = header.substring(7); // Extract the token
//        try {
//            SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JwtConstant.SECRET_KEY));
//
//            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            String email = claims.get("email", String.class);
//            String authorities = claims.get("authorities", String.class);
//            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (Exception e) {
//            throw new BadCredentialsException("Invalid token", e);
//        }
//    }
//    filterChain.doFilter(request, response);
//}
//}
