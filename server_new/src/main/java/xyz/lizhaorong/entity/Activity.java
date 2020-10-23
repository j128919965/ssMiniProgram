package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "activity")
public class Activity implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    private String fromdate;

    private String todate;

    private String date;

    private String level;

    private String backimg;

    private String warning;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return fromdate
     */
    public String getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate
     */
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return todate
     */
    public String getTodate() {
        return todate;
    }

    /**
     * @param todate
     */
    public void setTodate(String todate) {
        this.todate = todate;
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

    /**
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return backimg
     */
    public String getBackimg() {
        return backimg;
    }

    /**
     * @param backimg
     */
    public void setBackimg(String backimg) {
        this.backimg = backimg;
    }

    /**
     * @return warning
     */
    public String getWarning() {
        return warning;
    }

    /**
     * @param warning
     */
    public void setWarning(String warning) {
        this.warning = warning;
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