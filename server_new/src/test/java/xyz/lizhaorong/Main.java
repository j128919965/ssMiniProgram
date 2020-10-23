package xyz.lizhaorong;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String date = DateUtil.format(new Date(),"yyyy-MM-dd");
        System.out.println(date);
    }
}
