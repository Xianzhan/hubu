package xianzhan.hubu.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xianzhan.hubu.base.pojo.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianzhan
 * @since 2023-03-11
 */
public class JsonUtilTest {

    @Test
    public void testToJson() throws ParseException {
        // "null"
        String json = JsonUtil.toJson(null);
        Assertions.assertEquals("null", json);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-12-25 18:48:00");

        // obj
        Student student = new Student();
        student.setName("xianzhan");
        student.setAge(10);
        student.setBirthday(date);

        json = JsonUtil.toJson(student);
        Assertions.assertEquals("{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"}", json);

        // list
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student);
        json = JsonUtil.toJson(list);
        Assertions.assertEquals("[{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"},{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"}]", json);

        // map
        Map<String, Student> map = HashMap.newHashMap(1);
        map.put("xianzhan", student);
        json = JsonUtil.toJson(map);
        Assertions.assertEquals("{\"xianzhan\":{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"}}", json);
    }

    @Test
    public void testToObj() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String json = "{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 04:29:25\"}";
        Student student = JsonUtil.toObj(json, Student.class);
        Assertions.assertNotNull(student);
        Assertions.assertEquals("xianzhan", student.getName());
        Assertions.assertEquals(10, student.getAge());
        Assertions.assertEquals("2022-12-25 04:29:25", sdf.format(student.getBirthday()));

        System.out.println(student.getBirthday());
    }

    @Test
    public void testToList() {
        String json = "[{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"},{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"}]";
        List<Student> students = JsonUtil.toList(json, Student.class);
        Assertions.assertEquals(2, students.size());
    }

    @Test
    public void testToMap() {
        String json = "{\"xianzhan\":{\"name\":\"xianzhan\",\"age\":10,\"birthday\":\"2022-12-25 18:48:00\"}}";
        Map<String, Student> map = JsonUtil.toMap(json, String.class, Student.class);
        Assertions.assertEquals(1, map.size());
        Assertions.assertTrue(map.containsKey("xianzhan"));
    }
}
