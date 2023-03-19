package com.example.myblogv1.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
//    @Value("${myblog.app.secret}")
//    private  String APP_SECRET;

// Generate a random key
byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
    @Value("${myblog.expires.in}")
    private  Long EXPIRES_IN;

    /* SUBJECT => USER
        setIssuedAt => KEY NE ZAMAN OLUŞTURULDU
        signWith => app secret oluşturmak için kullaanılan algooritmalar
        getBody() => çözdüğümüz key içerisindeki ıd date subject vs
        getSubject() => long a parse ediyoruz

     */
//    SecretKeyFor(SignatureAlgorithm.HS512

    public  String generateJwtToken(Authentication auth ){
        JwtUserDetails userDetails = (JwtUserDetails)  auth.getPrincipal();
        Date expireDate =  new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date()).setExpiration(expireDate).signWith(Keys.hmacShaKeyFor(key)).compact();
        //USER ID YERINE USERNAME DE KULLANILABILIR BU KEY I OLUŞTURMAK İÇİN
    }
//KEY DEN USER ID GERİ ALMA - PARSE AND VERIFY
        Long getUserIdFromJwt (String token){

            Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key)).build().parseClaimsJws(token);

            return Long.parseLong(claims.getSubject());
        }
        //elde edilen token valid mi diye kontrol ediyoruz.
        boolean  validateToken(String token){
        try {
//            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key)).build().parseClaimsJws(token);
//            Claims claims = parser.parseClaimsJws(token).getBody();

            //eğer parse edilebiliyorsa uygulamamızın tokenıdır.
            return  !isTokenExpired(token);
        }
        catch (  MalformedJwtException | UnsupportedJwtException | IllegalArgumentException |
                ExpiredJwtException e){
            return  false;
        }
        }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key)).build().parseClaimsJws(token).getBody().getExpiration();
        return  expiration.before(new Date());
    }

}

