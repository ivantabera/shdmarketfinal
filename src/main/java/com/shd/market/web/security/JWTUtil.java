package com.shd.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final  String KEY = "stayhomedeveloping";

    public String generateToken(UserDetails userDetails){

        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /*
    * Validar si el token es correcto
    * */
    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUserName(token)) && !isTokenExpired(token);
    }

    /*
    * Metodo que se encarga de extraer el nombre del usuario
    * */
    public String extractUserName(String token){
        return getClaims(token).getSubject();
    }

    /*
    * Metodo que verifica si el token ya expiro
    * */
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    /*
    * Metodo auxiliar que retorna los claims son los objetos del JWT*/
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
