package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String time;

    private Boolean read;

    /**
     * 接收方id
     */
    private Integer to;

    private String title;

    private String context;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return read
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * @param read
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * 获取接收方id
     *
     * @return to - 接收方id
     */
    public Integer getTo() {
        return to;
    }

    /**
     * 设置接收方id
     *
     * @param to 接收方id
     */
    public void setTo(Integer to) {
        this.to = to;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }
}