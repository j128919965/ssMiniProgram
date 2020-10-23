package xyz.lizhaorong.entity.st;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "st_groups")
public class Group implements Serializable {

    @Id
    private Integer id;
    private Integer point;
    private String name;


}
