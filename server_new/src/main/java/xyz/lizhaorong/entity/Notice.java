package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "notice")
public class Notice implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String title;

    private String img;

    private String type;

    private String url;

    private Integer level;

    private Boolean visiable;

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
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return visiable
     */
    public Boolean getVisiable() {
        return visiable;
    }

    /**
     * @param visiable
     */
    public void setVisiable(Boolean visiable) {
        this.visiable = visiable;
    }
}