package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "swiper")
public class Swiper implements Serializable {
    @Id
    private String url;

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
}