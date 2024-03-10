import java.util.ArrayList;
import java.util.List;

/**
 * @classname: ListTest
 * @author: zhou
 * @description:
 * @date: 2023/5/16 14:02
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(3);
        list.add("1");
        list.add("2");
        for (String s : list) {
            if (s.equals("2")){
                list.remove(s);
            }
        }
    }
}
