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

/**
 * Filter for authorization
 */
public class AuthorizationFilter extends OncePerRequestFilter {
    /**
     * <p>Filters the request for the not permitted routes</p>
     * <p>Checks if the user has provided a valid access_token in the Authorization header</p>
     * @param request The request on which the filter works
     * @param response The response in the filterChain
     * @param filterChain The filterChain on which the filter is applied
     * @throws IOException If there goes something wrong writing to the response
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Read the value of the Authorization header
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Get the servletPath from the request
        String servletPath = request.getServletPath();

        // The user only has to be authenticated if the routed is protected and the user has a token provided
        if (!servletPath.matches(PermittedRoutes.toRegex()) && JwtTokenUtils.hasBearerToken(authorizationHeader)) {
            try {
                // Get the decoded instance from the token
                DecodedJWT decodedJWT = JwtTokenUtils.decodeJwt(authorizationHeader);

                // Get the granted authorities of the user
                String username = decodedJWT.getSubject();
                List<SimpleGrantedAuthority> authorities = stream(decodedJWT.getClaim("roles").asArray(String.class))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                // Create a new UsernamePasswordAuthenticationToken instance and set it in the security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                AuthFilterExceptionHandler.handle(e, response);
                return;
            }
        }

        // Execute the next filter on the filterChain
        filterChain.doFilter(request, response);
    }
}
