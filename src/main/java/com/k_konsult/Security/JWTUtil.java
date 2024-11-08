package com.k_konsult.Security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Payload;

@Component
public class JWTUtil {
    private final String SECRET_KEY = "sdf4sad4*+5f47caFO]O<>ER&PSD%^#@DSKM";
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
    public String generateToken(String username , String password) {
        JWTCreator.Builder builder = JWT.create()
            .withClaim("username", username)
            .withClaim("password", password)
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        return builder.sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return getClaimFromToken(token, "username");
    }
    public String extractPassword(String token) {
        return getClaimFromToken(token, "password");
    }
    private String getClaimFromToken(String token, String claimName) {
        Payload payload = JWT.decode(token);
        Claim claim = payload.getClaim(claimName);
        return claim.asString();
    }

    private boolean isTokenExpired(String token) {
        Payload payload = JWT.decode(token);
        Claim expirationClaim = payload.getClaim("exp");
        Date expirationDate = expirationClaim.asDate();
        return expirationDate.before(new Date());
    }

    public boolean isTokenExpiredDate(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                    .build(); // Reusable verifier
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expirationDate = decodedJWT.getExpiresAt();
            return expirationDate.after(new Date());
        } catch (Exception e) {
            // Handle the exception, possibly the token is invalid or issuer verification failed
            return false; // Consider token expired if verification fails
        }
    }
}