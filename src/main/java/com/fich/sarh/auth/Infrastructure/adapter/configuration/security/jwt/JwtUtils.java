package com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.filter.JwtTokenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret.key}")
    private String privateKey;

    @Value("${jwt.user.generator}")
    private String userGenerator;

    @Value("${jwt.time.expiration}")
    private Long expiration;
    @Value("${jwt.time.refreshExpiration}")
    private Long expiryDate;

    private Algorithm getAlgoritm(){
        return Algorithm.HMAC256(this.privateKey);
    }

    public String createToken(Authentication authentication) {

        String username = extractUsernameFromAuth(authentication);

        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // READ,WRITE,
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + this.expiration);

        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withClaim("type", "access")
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withJWTId(UUID.randomUUID().toString())
                .sign(getAlgoritm());


    }

    public DecodedJWT validateToken(String token) {


        try {


            JWTVerifier verifier = JWT.require(getAlgoritm())
                    .withIssuer(this.userGenerator)
                    .withClaim("type", "access")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            logger.info("DATOS VALIDOS " + decodedJWT.getSubject());

            return decodedJWT;

        } catch (JWTVerificationException e) {
            logger.error("Access token inválido o expirado: {}", e.getMessage());
            throw new JWTVerificationException("Token Invalid, not Authorized");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT) {

        return decodedJWT.getSubject().toString();
    }

    public String extractAuthorities(DecodedJWT decodedJWT) {

        return decodedJWT.getClaims().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }


    public String createRefreshToken(Authentication authentication) {
     //  Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = this.extractUsernameFromAuth(authentication);
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + this.expiryDate);

        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("type", "refresh")
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withJWTId(UUID.randomUUID().toString())
               // .withNotBefore(now)
                .sign(getAlgoritm());
    }

    public DecodedJWT validateRefreshToken(String refreshToken){
        try {

            JWTVerifier verifier = JWT.require(getAlgoritm())
                    .withIssuer(this.userGenerator)

                    .withClaim("type", "refresh")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            logger.debug("Refresh token válido para usuario: {}", decodedJWT.getSubject());
            return  decodedJWT;
        }catch (JWTVerificationException e){
           logger.error("Refresh token inválido o expirado: {}", e.getMessage());
           throw new JWTVerificationException("Refresh token invalido o expirado");
        }
    }
    public boolean isRefreshToken(DecodedJWT decodedJWT){
        if(decodedJWT == null) return false;
        Claim typeClaim = decodedJWT.getClaim("type");
        return "refresh".equals(typeClaim.asString());
    }

    public boolean isAccessToken(DecodedJWT decodedJWT){
        if(decodedJWT == null) return false;
        Claim typeClaim = decodedJWT.getClaim("type");
        return  "access".equals(typeClaim.asString());
    }

    /**
     * Método auxiliar seguro para obtener el username del Authentication.
     */
    private String extractUsernameFromAuth(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails userDetails) {
            return userDetails.getUsername();
        } else if (principal instanceof String username) {
            return username;
        } else {
            throw new IllegalArgumentException("No se pudo extraer el username del Authentication");
        }
    }



}
