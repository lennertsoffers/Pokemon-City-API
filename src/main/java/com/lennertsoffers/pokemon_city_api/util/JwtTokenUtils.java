package com.lennertsoffers.pokemon_city_api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

public class JwtTokenUtils {
    public static int DEFAULT_JWT_ALIVE_TIME = TimeUtils.minToMilliseconds(1);
    public static int DEFAULT_REFRESH_ALIVE_TIME = TimeUtils.daysToMilliseconds(15);

    private static final String KEY = "/!JC&!GvnPa]5P-@6u/X%_WmtP27bGDn/9$j)+JA#2$,aVcf!25hk7*,5!qrHxVXaQ(yc]Kaz{!]JRV&";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(KEY.getBytes());

    public static String genToken(String subject, String claimName, List<String> claimValue, String issuer, int validFor) {
        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + validFor))
                .withIssuer(issuer)
                .withClaim(claimName, claimValue)
                .sign(ALGORITHM);
    }

    public static DecodedJWT decodeJwt(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(ALGORITHM).build();

        return verifier.verify(token);
    }

    public static boolean hasBearerToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
