package com.lennertsoffers.pokemon_city_api.security;

import com.lennertsoffers.pokemon_city_api.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filter for authentication
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // Set the url for the login endpoint to '/auth/login' instead of '/login'
        setFilterProcessesUrl("/auth/login");
    }

    /**
     * Authenticates the login request by authenticating the username and password
     * @param request The request containing the username and the password
     * @param response The response in the filterChain
     * @return The authentication instance by calling authenticate on the authenticationManager
     * @throws AuthenticationException If the authentication is not successful
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * <p>Handles a successful authentication of the {@link #attemptAuthentication(HttpServletRequest, HttpServletResponse)}</p>
     * <p>Adds the access_token and the refresh_token to the response</p>
     * @param request The httpRequest
     * @param response The response in the filterChain
     * @param chain The filterChain on which the filter is applied
     * @param authentication The authentication instance produced by the {@link #attemptAuthentication(HttpServletRequest, HttpServletResponse)}
     * @throws IOException If there is a problem writing to the response
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        String subject = user.getUsername();
        List<String> claimValue = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String issuer = request.getRequestURL().toString();

        JwtTokenUtils.addTokensToResponse(subject, claimValue, issuer, response);
    }
}
