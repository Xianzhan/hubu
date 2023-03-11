package xianzhan.hubu.base.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author xianzhan
 * @since 2023-03-11
 */
@Data
public class Student {
    private String name;
    private int    age;
    private Date   birthday;
}
