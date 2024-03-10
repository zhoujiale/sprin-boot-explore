import java.util.HashMap;

/**
 * @classname: MapTest
 * @author: zhou
 * @description:
 * @date: 2023/5/15 17:23
 */
public class MapTest {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < 70;i++){
            map.put(String.valueOf(i),i);
        }
    }
}
