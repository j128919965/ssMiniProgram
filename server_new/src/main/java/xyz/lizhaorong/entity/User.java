package xyz.lizhaorong.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer uid;

    private String cn;

    private String name;

    /**
     * picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
     */
    private Integer department;

    private Boolean gender;

    private String birthday;

    private String className;

    private String stuId;

    private String wxid;

    private Boolean role;

    private String level;

    private String phone;

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
     * @return cn
     */
    public String getCn() {
        return cn;
    }

    /**
     * @param cn
     */
    public void setCn(String cn) {
        this.cn = cn;
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
     * 获取picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
     *
     * @return department - picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * 设置picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
     *
     * @param department picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }

    /**
     * @return gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return stuId
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * @param stuId
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * @return wxid
     */
    public String getWxid() {
        return wxid;
    }

    /**
     * @param wxid
     */
    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    /**
     * @return role
     */
    public Boolean getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(Boolean role) {
        this.role = role;
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
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", cn='" + cn + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", className='" + className + '\'' +
                ", stuId='" + stuId + '\'' +
                ", wxid='" + wxid + '\'' +
                ", role=" + role +
                ", level='" + level + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}