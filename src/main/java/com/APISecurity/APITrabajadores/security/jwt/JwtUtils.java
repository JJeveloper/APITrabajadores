package com.APISecurity.APITrabajadores.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKEY;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    //generar acceso de token
    public String generarToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//una hora
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //obtener firma del token
    public Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //validar token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //obtener todos los claimbs del token
    public Claims obtenerClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //obtener username
    public String extraerUsername(String token){
        return  obtenerClaims(token).getSubject();
    }

    //obtener un claim especifico
    public <T> T extraerClaim(String token, Function<Claims,T> extrear){
        final Claims claims = obtenerClaims(token);
        return extrear.apply(claims);
    }

}
