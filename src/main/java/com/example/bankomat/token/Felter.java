package com.example.bankomat.token;

import com.example.bankomat.service.XodimService;
import com.example.bankomat.token.Token_Olish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Felter extends OncePerRequestFilter {
@Lazy
    @Autowired
    Token_Olish token_olish;
    @Autowired
    XodimService xodimService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String auth = request.getHeader("auth");
        if (auth!=null) {
            String username = token_olish.dishifrovka(auth);
            if (username != null) {
                final UserDetails userDetails = xodimService.loadUserByUsername(username);
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                System.out.println("Username yaratilmadi tokin buzilgan!!!");
            }
        }
        else {
            System.out.println("token ololmadi!!!!!");
        }
        filterChain.doFilter(request,response);
    }
}
