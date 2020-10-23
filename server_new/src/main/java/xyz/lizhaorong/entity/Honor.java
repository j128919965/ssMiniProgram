package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "honor")
public class Honor implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    private String src;

    private String description;

    @Column(name = "get_method")
    private String getMethod;

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
     * @return src
     */
    public String getSrc() {
        return src;
    }

    /**
     * @param src
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return get_method
     */
    public String getGetMethod() {
        return getMethod;
    }

    /**
     * @param getMethod
     */
    public void setGetMethod(String getMethod) {
        this.getMethod = getMethod;
    }
}