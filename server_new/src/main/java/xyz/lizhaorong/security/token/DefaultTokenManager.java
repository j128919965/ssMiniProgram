package xyz.lizhaorong.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.lizhaorong.security.token.entity.SimpleUser;
import xyz.lizhaorong.security.token.entity.TokenErrorCode;
import xyz.lizhaorong.security.token.entity.TokenObject;
import xyz.lizhaorong.util.support.ErrorCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("tokenManager")
@Slf4j
public class DefaultTokenManager implements TokenManager {

    public static final int MAX_COUNT = 5;

    /**
     * accessToken 过期时间 60min
     */
    private static final long EXPIRE_TIME=60 * 60 *1000;

    /**
     * refreshToken 过期时间 90min
     */
    private static final long REFRESH_EXPIRE_TIME=90 * 60 *1000;

    /**
     * 加密秘钥，使用它生成token
     */
    private static final String TOKEN_SECRET="4kd2js1kl4mmc5kd4sa4x54e8w/d18as-+asc2DDX2D8gf";

    private static final String REFRESH_TOKEN_SECRET="SOFG,ZX9-S8Q2+X5R4+672FS9MPV;Z.PQ*/91`SA56CZ5@$^%&(@sgjxasfw";


    private SimpleUser analysisToken(String token,boolean flag){
        try{

            Algorithm algorithm ;
            if(flag){
                algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            }else{
                algorithm = Algorithm.HMAC256(REFRESH_TOKEN_SECRET);
            }
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return new SimpleUser(
                    decodedJWT.getClaim("uid").asString()
                    , decodedJWT.getClaim("addr").asString()
                    ,decodedJWT.getClaim("role").asInt()
                    ,decodedJWT.getClaim("count").asInt()
            );
        }catch (TokenExpiredException e){
            return new SimpleUser(null,null,0,-1);
        }catch (JWTDecodeException e){
            System.out.println("token解析失败");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TokenObject generate(SimpleUser user) {
        String acc = generateAToken(user,true);
        String ref = generateAToken(user,false);
        return new TokenObject(acc,ref);
    }

    private String generateAToken(SimpleUser user,boolean isAccess){
        try{
            Date date;
            Algorithm algorithm ;
            if(isAccess){
                date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
                algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            }else{
                date = new Date(System.currentTimeMillis()+REFRESH_EXPIRE_TIME);
                algorithm = Algorithm.HMAC256(REFRESH_TOKEN_SECRET);
            }
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("uid",user.getUserId())
                    .withClaim("role",user.getRole())
                    .withClaim("addr",user.getAddr())
                    .withClaim("count",user.getCount())
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TokenObject refresh(String refreshToken) {
        Object o = checkAuthorizationImpl(refreshToken,0,null,false);
        if(o instanceof ErrorCode){
            log.debug("refresh failed");
            return null;
        }
        SimpleUser user = (SimpleUser)o;
        user.setCount(user.getCount()+1);
        if(user.getCount()>MAX_COUNT){
            log.debug("over the max refresh times");
            return null;
        }
        return new TokenObject(generateAToken(user,true),generateAToken(user,false));

    }

    @Override
    public ErrorCode checkAuthorization(String accessToken, int role,String addr) {
        Object o = checkAuthorizationImpl(accessToken,role,addr,true);
        if(o instanceof ErrorCode){
            return (ErrorCode) o;
        }
        return null;
    }

    private Object checkAuthorizationImpl(String authorization, int role, String addr,boolean isAccess){

        //token是否为空
        if(authorization==null) return TokenErrorCode.DID_NOT_LOGIN;

        //获取token解析结果
        SimpleUser user;
        user = analysisToken(authorization,isAccess);

        if(user==null) return TokenErrorCode.WRONG_TOKEN;

        //令牌需要刷新
        if(user.getCount()==-1) return TokenErrorCode.NEED_REFRESH;

        //需要重新登录
        if(user.getCount()== DefaultTokenManager.MAX_COUNT) return TokenErrorCode.NEED_LOGIN;

        if(isAccess){
            //检查地址是否一致
            if(!addr.equals(user.getAddr())) return TokenErrorCode.WRONG_ADDR;

            //检查接口权限
            if (user.getRole()<role)return TokenErrorCode.INSUFFICIENT_AUTHORITY;
        }
        return user;

    }
}
