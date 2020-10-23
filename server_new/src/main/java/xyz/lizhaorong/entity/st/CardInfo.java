package xyz.lizhaorong.entity.st;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "st_card_info")
public class CardInfo implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer type;
    private String name;
    private Integer prop;
    private Integer point;
    private String url;

}
