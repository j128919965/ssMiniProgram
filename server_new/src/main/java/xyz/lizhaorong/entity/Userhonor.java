package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "userhonor")
public class Userhonor implements Serializable {
    @Id
    private Integer uid;

    @Id
    private Integer hid;

    private String date;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return hid
     */
    public Integer getHid() {
        return hid;
    }

    /**
     * @param hid
     */
    public void setHid(Integer hid) {
        this.hid = hid;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
}