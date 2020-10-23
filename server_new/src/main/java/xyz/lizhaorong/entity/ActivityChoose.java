package xyz.lizhaorong.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "activity_choose")
public class ActivityChoose {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer uid;

    private Integer aid;

    private Date time;

    private Boolean handled;

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
     * @return aid
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * @param aid
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return handled
     */
    public Boolean getHandled() {
        return handled;
    }

    /**
     * @param handled
     */
    public void setHandled(Boolean handled) {
        this.handled = handled;
    }
}