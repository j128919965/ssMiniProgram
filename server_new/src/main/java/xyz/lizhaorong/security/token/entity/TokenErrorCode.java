package xyz.lizhaorong.security.token.entity;

import xyz.lizhaorong.util.support.ErrorCode;

public class TokenErrorCode extends ErrorCode {
    public static final TokenErrorCode DID_NOT_LOGIN = new TokenErrorCode("没有检测到登录信息", 1);
    public static final TokenErrorCode WRONG_TOKEN = new TokenErrorCode("登录信息无效", 2);
    public static final TokenErrorCode NEED_REFRESH = new TokenErrorCode("令牌需要刷新", 3);
    public static final TokenErrorCode NEED_LOGIN = new TokenErrorCode("需要重新登录",4);
    public static final TokenErrorCode WRONG_ADDR = new TokenErrorCode("和上次登录地址不同", 5);
    public static final TokenErrorCode INSUFFICIENT_AUTHORITY = new TokenErrorCode("您的账号权限不足", 6);


    private TokenErrorCode(String message, int code) {
        super(message, code);
    }
}