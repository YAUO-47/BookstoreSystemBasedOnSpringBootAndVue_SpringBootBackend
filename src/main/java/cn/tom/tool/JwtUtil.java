package cn.tom.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    //签名的加密的密码， 服务器知道即可
    private final static String JWT_SECRET = "qwer1234";
    // 过期时间120分钟
    private final static long EXPIRE_TIME = 120 * 60 * 1000;


    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username, String role,int id,String img) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET );
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("role", role)
                    .withClaim("id", id)
                    .withClaim("img", img)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 检查Token是否有效
     *
     * @param token Token
     * @return 是否有效
     */
    public static boolean isValid(String token) {
        if (token != null & token.length()>1) {
            try {
                //创建验证对象,这里使用的加密算法和密钥必须与生成TOKEN时的相同否则无法验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
                //验证JWT
                DecodedJWT decodedJwt = jwtVerifier.verify(token);
                return new Date().before(decodedJwt.getExpiresAt());
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static String getImg(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("img").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getRole(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return  jwt.getClaim("role").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static int getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return  jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return 0;
        }
    }
}
