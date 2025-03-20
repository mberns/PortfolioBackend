package com.portfolio.martina_b.Security.jwt;

import com.portfolio.martina_b.Security.Entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwt;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

@Component
public class JwtProvider { // genera el token y tiene metodos de validacion para ver si esta bien armado o no el mismo

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}") //la va a traer de application.properties
    private String secret;
    @Value("${jwt.expiration}") //la va a traer de application.properties
    private int expiration;

    //new date es para marcar la fecha de inicio
    //lo de signature algorithm es el algoritmo de firma, en este caso el hs512, que se va  a 
    //guardar en secret
    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().subject(usuarioPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), Jwts.SIG.HS512)
                .compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //El token esta formado por 3 partes, si alguna de esas partes esta mal, tira error
    public boolean validateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        }catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        }catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        }catch (IllegalArgumentException e) {
            logger.error("Token vacio");
        }catch (SignatureException e) {
            logger.error("Firma no valida");
        }
        return false;
    }
    
}
