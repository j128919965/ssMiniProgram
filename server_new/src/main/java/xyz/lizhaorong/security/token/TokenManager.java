package xyz.lizhaorong.security.token;

import xyz.lizhaorong.security.token.entity.SimpleUser;
import xyz.lizhaorong.security.token.entity.TokenObject;
import xyz.lizhaorong.util.support.ErrorCode;

public interface TokenManager {

    /**
     * 生成token（字符串组）
     */
    TokenObject generate(SimpleUser user);

    /**
     * 通过refreshToken进行刷新
     */
    TokenObject refresh(String refreshToken);

    /**
     * 检查accessToken
     */
    ErrorCode checkAuthorization(String accessToken,int role,String addr);

}
