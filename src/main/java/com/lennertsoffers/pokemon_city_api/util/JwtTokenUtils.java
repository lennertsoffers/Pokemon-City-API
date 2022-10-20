package com.lennertsoffers.pokemon_city_api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collection of Util methods for JWT tokens
 */
public class JwtTokenUtils {
    public static int DEFAULT_JWT_ALIVE_TIME = TimeUtils.minToMilliseconds(120);
    public static int DEFAULT_REFRESH_ALIVE_TIME = TimeUtils.daysToMilliseconds(15);
    private static final String KEY = "/!JC&!GvnPa]5P-@6u/X%_WmtP27bGDn/9$j)+JA#2$,aVcf!25hk7*,5!qrHxVXaQ(yc]Kaz{!]JRV&";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(KEY.getBytes());

    /**
     * Generates tokens for the user and writes it to the response
     * @param subject The username for which the token has to be generated
     * @param claimValue List of the roles of the user
     * @param issuer Request url on which the token is generated
     * @param response The response in the chain
     * @throws IOException If something goes wrong writing to the response
     */
    public static void addTokensToResponse(String subject, List<String> claimValue, String issuer, HttpServletResponse response) throws IOException {
        // Generate the tokens
        String accessToken = JwtTokenUtils.genToken(subject, "roles", claimValue, issuer, JwtTokenUtils.DEFAULT_JWT_ALIVE_TIME);
        String refreshToken = JwtTokenUtils.genToken(subject, "roles", claimValue, issuer, JwtTokenUtils.DEFAULT_REFRESH_ALIVE_TIME);

        // Create a map with the access and refresh tokens
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        // Write the tokens to the response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    /**
     * Generates a JWT
     * @param subject The username for which the token has to be generated
     * @param claimValue List of the roles of the user
     * @param issuer Request url on which the token is generated
     * @param validFor Amount of milliseconds the token will be valid
     * @return The generated JWT
     */
    public static String genToken(String subject, String claimName, List<String> claimValue, String issuer, int validFor) {
        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + validFor))
                .withIssuer(issuer)
                .withClaim(claimName, claimValue)
                .sign(ALGORITHM);
    }

    /**
     * Decodes and verifies the JWT in the header
     * @param authorizationHeader The Authorization header from the request
     * @return The Decoded JWT
     */
    public static DecodedJWT decodeJwt(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(ALGORITHM).build();

        return verifier.verify(token);
    }

    /**
     * Checks if the authorizationHeader contains a bearer token
     * @param authorizationHeader The Authorization header from the request
     * @return True if the header contains a Bearer token
     */
    public static boolean hasBearerToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
