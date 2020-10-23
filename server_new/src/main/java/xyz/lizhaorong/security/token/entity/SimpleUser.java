package xyz.lizhaorong.security.token.entity;

import lombok.Data;

@Data
public class SimpleUser {

    private String userId;
    private String addr;
    private int role;
    private int count;

    public SimpleUser(String userId, String addr, int role, int count) {
        this.userId = userId;
        this.addr = addr;
        this.role = role;
        this.count = count;
    }
}
