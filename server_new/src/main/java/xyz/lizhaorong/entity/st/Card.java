package xyz.lizhaorong.entity.st;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "st_card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    Integer id;
    Integer type;
    Integer group;

}
