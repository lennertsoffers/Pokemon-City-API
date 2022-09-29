package com.lennertsoffers.pokemon_city_api.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lennertsoffers.pokemon_city_api.exception.AuthFilterExceptionHandler;
import com.lennertsoffers.pokemon_city_api.util.JwtTokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.stream;

public class AuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String servletPath = request.getServletPath();

        if (!servletPath.matches(PermittedRoutes.toRegex()) && JwtTokenUtils.hasBearerToken(authorizationHeader)) {
            try {
                DecodedJWT decodedJWT = JwtTokenUtils.decodeJwt(authorizationHeader);

                String username = decodedJWT.getSubject();
                List<SimpleGrantedAuthority> authorities = stream(decodedJWT.getClaim("roles").asArray(String.class))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                AuthFilterExceptionHandler.handle(e, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }


}
