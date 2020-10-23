package xyz.lizhaorong.security.token.entity;

import lombok.Data;

@Data
public class TokenObject {
    private String accessToken;
    private String refreshToken;

    public TokenObject(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
