package xyz.lizhaorong.entity.st;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "st_select")
@Data
public class GroupSelect implements Serializable {

    @Id
    private Integer uid;

    private Integer gid;

    private Boolean master;


}
