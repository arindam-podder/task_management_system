package com.arindam.tms.filters;

import com.arindam.tms.security.jwt.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);
            try{
                userName = this.jwtHelper.getUserNameFromToken(token);
            }catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            logger.info("Invalid request. ");
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if(validateToken){
                //set the authentication details
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }else{
                System.out.println("Validation fails !!");
            }
        }

        filterChain.doFilter(request, response);

    }//doFilterInternal end
}